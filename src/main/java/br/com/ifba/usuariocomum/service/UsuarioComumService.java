/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuariocomum.service;

import br.com.ifba.usuariocomum.entity.UsuarioComum;
import br.com.ifba.usuariocomum.repository.UsuarioComumRepository;
import br.com.ifba.util.RegraNegocioException;
import br.com.ifba.util.StringUtil;
import jakarta.persistence.PersistenceException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */

@Service
@Slf4j
public class UsuarioComumService {

    @Autowired
    private UsuarioComumRepository repo;

    public boolean save(UsuarioComum user) {
        validarUsuarioComum(user);
        try {
            repo.save(user);
            return true;
        } catch (PersistenceException e) {
            log.error("Erro ao salvar usuário comum no banco de dados.", e);
            throw new RegraNegocioException("Erro ao salvar usuário comum no banco de dados.", e);
        }
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.error("ID de usuário comum inválido para exclusão.");
            throw new RegraNegocioException("ID de usuário comum inválido para exclusão.");
        }

        try {
            repo.deleteById(id);
        } catch (PersistenceException e) {
            log.error("Erro ao excluir usuário comum do banco de dados.", e);
            throw new RegraNegocioException("Erro ao excluir usuário comum do banco de dados.", e);
        }
    }

    public List<UsuarioComum> findAll() {
        try {
            return repo.findAll();
        } catch (PersistenceException e) {
            log.error("Erro ao buscar todos os usuários comuns.", e);
            throw new RegraNegocioException("Erro ao buscar todos os usuários comuns.", e);
        }
    }

    public List<UsuarioComum> findByNome(String nome) {
        if (StringUtil.isNullOrEmpty(nome)) {
            log.error("Nome inválido.");
            throw new RegraNegocioException("Informe um nome válido para a busca.");
        }

        try {
            return repo.findByNomeContainingIgnoreCase(nome);
        } catch (PersistenceException e) {
            log.error("Erro ao buscar usuário comum por nome.", e);
            throw new RegraNegocioException("Erro ao buscar usuário comum por nome.", e);
        }
    }

    public UsuarioComum findById(Long id) {
        if (id == null || id <= 0) {
            log.error("ID inválido.");
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return repo.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário comum não encontrado."));
        } catch (PersistenceException e) {
            log.error("Erro ao buscar usuário comum por ID.", e);
            throw new RegraNegocioException("Erro ao buscar usuário comum por ID.", e);
        }
    }

    // Validação de regra de negócio
    private void validarUsuarioComum(UsuarioComum user) {
        if (user == null) {
            log.error("Usuário comum é nulo.");
            throw new RegraNegocioException("O usuário comum não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(user.getCpf_Cnpj())) {
            log.error("CPF/CNPJ é obrigatório.");
            throw new RegraNegocioException("O CPF/CNPJ é obrigatório.");
        }

        if (StringUtil.isNullOrEmpty(user.getNome()) ||
            !StringUtil.hasValidLength(user.getNome(), 3, 100)) {
            log.error("Nome do usuário comum inválido.");
            throw new RegraNegocioException("O nome deve ter entre 3 e 100 caracteres.");
        }

        if (!StringUtil.isValidEmail(user.getEmail())) {
            log.error("E-mail inválido.");
            throw new RegraNegocioException("Email inválido.");
        }

        if (StringUtil.isNullOrEmpty(user.getTelefone())) {
            log.error("Telefone é obrigatório.");
            throw new RegraNegocioException("O telefone é obrigatório.");
        }

        if (StringUtil.isNullOrEmpty(user.getSenha_Hash())) {
            log.error("Senha é obrigatória.");
            throw new RegraNegocioException("A senha é obrigatória.");
        }

        if (!StringUtil.isCpfOuCnpjValido(user.getCpf_Cnpj())) {
            throw new RegraNegocioException("CPF ou CNPJ inválido.");
        }

        // As preferências e permissões podem ter validações lógicas extras caso necessário.
    }
}