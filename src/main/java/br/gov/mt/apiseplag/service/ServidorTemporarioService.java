package br.gov.mt.apiseplag.service;

import br.gov.mt.apiseplag.model.ServidorTemporario;
import br.gov.mt.apiseplag.repository.ServidorTemporarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServidorTemporarioService {
    @Autowired
    private ServidorTemporarioRepository servidorTemporarioRepository;

    public List<ServidorTemporario> listarTodos() {
        return servidorTemporarioRepository.findAll();
    }

    public Optional<ServidorTemporario> buscarPorId(Long id) {
        return servidorTemporarioRepository.findById(id);
    }

    public ServidorTemporario salvar(ServidorTemporario servidorTemporario) {
        return servidorTemporarioRepository.save(servidorTemporario);
    }

    public void deletar(Long id) {
        servidorTemporarioRepository.deleteById(id);
    }
}
