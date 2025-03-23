package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.FotoPessoa;
import br.gov.mt.apiseplag.repository.FotoPessoaRepository;
import br.gov.mt.apiseplag.utils.FileUtils;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MinioService {

    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;

    private final MinioClient minioClient;
    private final String bucketName;

    public MinioService(
            @Value("${minio.url}") String url,
            @Value("${minio.access-key}") String accessKey,
            @Value("${minio.secret-key}") String secretKey,
            @Value("${minio.bucket}") String bucketName
    ) {
        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile file) throws Exception {
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (MinioException e) {
            throw new RuntimeException("Erro ao enviar arquivo para o MinIO -> "+e.getMessage());
        }
        return filename;
    }

    public String gerarUrlTemporaria(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(5, TimeUnit.MINUTES) // Tempo de expiração: 5 minutos
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URL temporária para: " + fileName, e);
        }
    }

    public FotoPessoa uploadFile(FotoPessoa fotoPessoa, Long idPessoa) {
        String base64Image = fotoPessoa.getFoto();  // O Base64 vindo da entidade FotoPessoa

        if (base64Image == null || base64Image.isEmpty()) {
            throw new IllegalArgumentException("O arquivo não pode ser nulo ou vazio.");
        }

        try {
            // Converte a string Base64 para um array de bytes
            byte[] fileBytes = Base64.getDecoder().decode(base64Image);

            // Gera um nome seguro para o arquivo
            String filename = idPessoa + "/" + UUID.randomUUID() + FileUtils.getFileExtensionFromBase64(base64Image);

            // Faz upload usando o array de bytes
            try (InputStream inputStream = new ByteArrayInputStream(fileBytes)) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(filename)
                                .stream(inputStream, fileBytes.length, -1)
                                .contentType(FileUtils.getContentType(base64Image))  // Ajuste para o tipo de arquivo, se necessário
                                .build()
                );

                // Atualiza os dados da FotoPessoa após o upload
                fotoPessoa.setData(LocalDate.now());
                fotoPessoa.setBucket(bucketName);
                fotoPessoa.setHash(calcularHash(fileBytes));

                // Salva a FotoPessoa no banco de dados
                return fotoPessoaRepository.save(fotoPessoa);

            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                throw new RuntimeException("Erro ao processar o arquivo: " + e.getMessage(), e);
            }
        } catch (MinioException e) {
            throw new RuntimeException("Erro ao enviar arquivo para o MinIO: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o arquivo Base64: " + e.getMessage(), e);
        }
    }

    // Método para calcular o hash SHA-256 do arquivo
    private String calcularHash(byte[] fileBytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(fileBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao calcular o hash do arquivo", e);
        }
    }

    public List<String> listarArquivosPorUsuario(String userId) throws Exception {
        List<String> arquivos = new ArrayList<>();

        try {
            // Prefixo para buscar os arquivos do usuário. Exemplo: "user-id/arquivo.jpg"
            String prefixo = userId + "/"; // Ajuste conforme sua organização de arquivos

            // Lista os objetos do bucket com o prefixo do usuário
            Iterable<Result<Item>> objects = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix(prefixo)  // Filtra os objetos que começam com o prefixo do usuário
                            .build()
            );

            // Itera sobre os objetos e coleta os nomes dos arquivos
            for (Result<Item> result : objects) {
                Item item = result.get();
                arquivos.add(item.objectName());  // Adiciona o nome do arquivo (ou o caminho completo)
            }

        } catch (MinioException e) {
            throw new RuntimeException("Erro ao listar arquivos do MinIO: " + e.getMessage(), e);
        }

        return arquivos;
    }
}
