/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.servidor.service;

import br.com.ifba.servidor.entity.TipoServidor;
import br.com.ifba.servidor.repository.TipoServidorRepository;
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
public class TipoServidorService {

    private final TipoServidorRepository tipoServidorRepository;

    public TipoServidor save(TipoServidor tipoServidor) {
        validarTipoServidor(tipoServidor);
        try {
            return tipoServidorRepository.save(tipoServidor);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao salvar TipoServidor: nome duplicado.", e);
            throw new RegraNegocioException("Já existe um tipo de servidor com esse nome.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao salvar TipoServidor.", e);
            throw new RegraNegocioException("Erro ao salvar Tipo de Servidor.");
        }
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido para exclusão de TipoServidor: {}", id);
            throw new RegraNegocioException("ID inválido.");
        }

        try {
            tipoServidorRepository.deleteById(id);
        } catch (RuntimeException e) {
            log.error("Erro ao excluir TipoServidor.", e);
            throw new RegraNegocioException("Erro ao excluir Tipo de Servidor.");
        }
    }

    public TipoServidor findById(Long id) {
        if (id == null || id <= 0) {
            throw new RegraNegocioException("ID inválido para busca.");
        }

        return tipoServidorRepository.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Tipo de Servidor não encontrado."));
    }

    public List<TipoServidor> findAll() {
        return tipoServidorRepository.findAll();
    }

    private void validarTipoServidor(TipoServidor tipoServidor) {
        if (tipoServidor == null) {
            throw new RegraNegocioException("Tipo de Servidor não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(tipoServidor.getNome())) {
            throw new RegraNegocioException("Nome do tipo de servidor é obrigatório.");
        }
    }
}
