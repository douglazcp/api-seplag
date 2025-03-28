package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.ServidorEfetivo;
import br.gov.mt.apiseplag.dto.ServidorEfetivoDto;
import br.gov.mt.apiseplag.dto.UnidadeDto;
import br.gov.mt.apiseplag.repository.ServidorEfetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServidorEfetivoService {
    @Autowired
    private ServidorEfetivoRepository servidorEfetivoRepository;

    @Autowired
    private FotoPessoaService fotoPessoaService;

    public List<ServidorEfetivo> listarTodos(PageRequest pageRequest) {
        Page<ServidorEfetivo> list = servidorEfetivoRepository.findAll(pageRequest);
        return list.stream().toList();
//        return servidorEfetivoRepository.findAll();
    }

    public Optional<ServidorEfetivo> buscarPorId(Long id) {
        return servidorEfetivoRepository.findById(id);
    }

    public ServidorEfetivo salvar(ServidorEfetivo servidorEfetivo) throws Exception {
        try {
            if(servidorEfetivo.getPessoa().getFotoPessoa() != null) {
                fotoPessoaService.salvar(servidorEfetivo.getPessoa().getFotoPessoa());
            }
            return servidorEfetivoRepository.save(servidorEfetivo);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void deletar(Long id) {
        servidorEfetivoRepository.deleteById(id);
    }

    public List<ServidorEfetivo> listarPorIdUnidade(int id) {
        return servidorEfetivoRepository.findAllByUnidade(id);
    }

    public List<ServidorEfetivoDto> mapearServidorEfetivoEmServidorEfetivoDto(List<ServidorEfetivo> servidoresEfetivos) {
        List<ServidorEfetivoDto> servidorEfetivoDtos = new ArrayList<>();
        ServidorEfetivoDto servidorEfetivoDto;
        for (ServidorEfetivo servidorEfetivo : servidoresEfetivos) {
            servidorEfetivoDto = new ServidorEfetivoDto();

            servidorEfetivoDto.setNome(servidorEfetivo.getPessoa().getNome());
            servidorEfetivoDto.setIdade(this.calcularIdade(servidorEfetivo.getPessoa().getDataNascimento()));
            UnidadeDto unidadeDto = new UnidadeDto();
            unidadeDto.setNome(servidorEfetivo.getPessoa().getPessoaEndereco().getEndereco().getCidade().getNome());
            servidorEfetivoDto.setUnidade(unidadeDto);
            servidorEfetivoDto.setFoto(servidorEfetivo.getPessoa().getFotoPessoa().getBucket());
            servidorEfetivoDtos.add(servidorEfetivoDto);
        }
        return servidorEfetivoDtos;
    }

    public int calcularIdade(LocalDate dataNascimento) {
        final LocalDate dataAtual = LocalDate.now();
        final Period periodo = Period.between(dataNascimento, dataAtual);
        return periodo.getYears();
    }
}
