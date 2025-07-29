/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.servidor.service;

import br.com.ifba.servidor.entity.Servidor;
import br.com.ifba.servidor.repository.ServidorRepository;
import br.com.ifba.util.RegraNegocioException;
import br.com.ifba.util.StringUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ServidorService {

    private final ServidorRepository servidorRepository;

    public Servidor save(Servidor servidor) {
        validarServidor(servidor);
        try {
            return servidorRepository.save(servidor);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao salvar Servidor: matrícula ou dados duplicados.", e);
            throw new RegraNegocioException("Já existe um servidor com essa matrícula ou dados inválidos.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao salvar Servidor.", e);
            throw new RegraNegocioException("Erro ao salvar servidor.");
        }
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido para exclusão de Servidor: {}", id);
            throw new RegraNegocioException("ID inválido.");
        }

        try {
            servidorRepository.deleteById(id);
        } catch (RuntimeException e) {
            log.error("Erro ao excluir servidor.", e);
            throw new RegraNegocioException("Erro ao excluir servidor.");
        }
    }

    public Servidor findById(Long id) {
        if (id == null || id <= 0) {
            throw new RegraNegocioException("ID inválido para busca.");
        }

        return servidorRepository.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Servidor não encontrado."));
    }

    public List<Servidor> findAll() {
        return servidorRepository.findAll();
    }

    public List<Servidor> findByNome(String nome) {
        return servidorRepository.findByNomeContainingIgnoreCase(nome);
    }

    private void validarServidor(Servidor servidor) {
        if (servidor == null) {
            log.warn("Servidor nulo.");
            throw new RegraNegocioException("Servidor não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(servidor.getNome())) {
            throw new RegraNegocioException("Nome do servidor é obrigatório.");
        }

        if (StringUtil.isNullOrEmpty(servidor.getMatricula())) {
            throw new RegraNegocioException("Matrícula do servidor é obrigatória.");
        }
    }
}
