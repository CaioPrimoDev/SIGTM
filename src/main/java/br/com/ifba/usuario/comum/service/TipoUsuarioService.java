/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.comum.service;

import br.com.ifba.usuario.comum.entity.TipoUsuario;
import br.com.ifba.usuario.comum.repository.TipoUsuarioRepository;
import br.com.ifba.util.RegraNegocioException;
import br.com.ifba.util.StringUtil;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TipoUsuarioService implements TipoUsuarioIService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public boolean save(TipoUsuario tipoUsuario) {
        validarTipoUsuario(tipoUsuario);
        try {
            tipoUsuarioRepository.save(tipoUsuario);
            return true;
        } catch (DataIntegrityViolationException e) {
            // Violação de constraints do banco (ex: unique ou not null)
            log.error("Violação de integridade ao salvar Tipo de Usuário: {}", tipoUsuario.getNome(), e);
            throw new RegraNegocioException("Já existe um tipo de usuário com esse nome ou dados inválidos.");
        } catch (RuntimeException e) {
            // Falha inesperada
            log.error("Erro inesperado ao salvar Tipo de Usuário.", e);
            throw new RegraNegocioException("Erro ao salvar Tipo de Usuário.");
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("Tentativa de excluir Tipo de Usuário com ID inválido: {}", id);
            throw new RegraNegocioException("ID de Tipo de Usuário inválido.");
        }

        try {
            tipoUsuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // ID não encontrado no banco
            log.error("Tentativa de exclusão de Tipo de Usuário inexistente (ID: {}).", id, e);
            throw new RegraNegocioException("Tipo de Usuário não encontrado para exclusão.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao excluir Tipo de Usuário.", e);
            throw new RegraNegocioException("Erro ao excluir Tipo de Usuário.");
        }
    }

    @Override
    public List<TipoUsuario> findAll() {
        try {
            return tipoUsuarioRepository.findAll();
        } catch (RuntimeException e) {
            log.error("Erro ao buscar todos os Tipos de Usuário.", e);
            throw new RegraNegocioException("Erro ao buscar todos os Tipos de Usuário.");
        }
    }

    @Override
    public TipoUsuario findById(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido fornecido para busca: {}", id);
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tipo de Usuário não encontrado para ID: {}", id);
                    return new RegraNegocioException("Tipo de Usuário não encontrado.");
                });
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao buscar Tipo de Usuário por ID.", e);
            throw new RegraNegocioException("Erro ao buscar Tipo de Usuário por ID.");
        }
    }

    @Override
    public void validarTipoUsuario(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) {
            log.warn("Tipo de Usuário recebido é nulo.");
            throw new RegraNegocioException("O Tipo de Usuário não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(tipoUsuario.getNome())) {
            log.warn("Nome do Tipo de Usuário é nulo ou vazio.");
            throw new RegraNegocioException("O nome do Tipo de Usuário é obrigatório.");
        }

        if (!StringUtil.hasValidLength(tipoUsuario.getNome(), 3, 30)) {
            log.warn("Nome do Tipo de Usuário fora do tamanho permitido: '{}'", tipoUsuario.getNome());
            throw new RegraNegocioException("O nome do Tipo de Usuário deve ter entre 3 e 30 caracteres.");
        }
    }
    
    @Override
    public TipoUsuario findByNome(String nome) {
        return tipoUsuarioRepository.findByNome(nome);
    }

}
