/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.service;

import br.com.ifba.gestor.entity.Gestor;
import br.com.ifba.gestor.repository.GestorRepository;
import br.com.ifba.util.RegraNegocioException;
import br.com.ifba.util.StringUtil;
import jakarta.persistence.PersistenceException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */

@Service
@Slf4j
@RequiredArgsConstructor//fazer injeção de dependências de forma automática
public class GestorService {

    private GestorRepository repo;

    public boolean save(Gestor gestor) {
        validarGestor(gestor);
        try {
            repo.save(gestor);
            return true;
        } catch (PersistenceException e) {
            log.error("Erro ao salvar gestor no banco de dados.", e);
            throw new RegraNegocioException("Erro ao salvar gestor no banco de dados.", e);
        }
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.error("ID de gestor inválido para exclusão.");
            throw new RegraNegocioException("ID de gestor inválido para exclusão.");
        }

        try {
            repo.deleteById(id);
        } catch (PersistenceException e) {
            log.error("Erro ao excluir gestor do banco de dados.", e);
            throw new RegraNegocioException("Erro ao excluir gestor do banco de dados.", e);
        }
    }

    public List<Gestor> findAll() {
        try {
            return repo.findAll();
        } catch (PersistenceException e) {
            log.error("Erro ao buscar todos os gestores.", e);
            throw new RegraNegocioException("Erro ao buscar todos os gestores.", e);
        }
    }

    public List<Gestor> findByNome(String nome) {
        if (StringUtil.isNullOrEmpty(nome)) {
            log.error("Nome inválido.");
            throw new RegraNegocioException("Informe um nome válido para a busca.");
        }

        try {
            return repo.findByNomeContainingIgnoreCase(nome);
        } catch (PersistenceException e) {
            log.error("Erro ao buscar gestor por nome.", e);
            throw new RegraNegocioException("Erro ao buscar gestor por nome.", e);
        }
    }

    public Gestor findById(Long id) {
        if (id == null || id <= 0) {
            log.error("ID inválido.");
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return repo.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Gestor não encontrado."));
        } catch (PersistenceException e) {
            log.error("Erro ao buscar gestor por ID.", e);
            throw new RegraNegocioException("Erro ao buscar gestor por ID.", e);
        }
    }

    // Validação de regra de negócio
    private void validarGestor(Gestor gestor) {
        if (gestor == null) {
            log.error("Gestor é nulo.");
            throw new RegraNegocioException("O gestor não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(gestor.getNome()) ||
            !StringUtil.hasValidLength(gestor.getNome(), 3, 100)) {
            log.error("Nome do gestor inválido.");
            throw new RegraNegocioException("O nome do gestor deve ter entre 3 e 100 caracteres.");
        }

        if (StringUtil.isNullOrEmpty(gestor.getMatricula_funcional())) {
            log.error("Matrícula funcional é obrigatória.");
            throw new RegraNegocioException("A matrícula funcional é obrigatória.");
        }

        if (StringUtil.isNullOrEmpty(gestor.getDepartamento())) {
            log.error("Departamento é obrigatório.");
            throw new RegraNegocioException("O departamento é obrigatório.");
        }

        if (StringUtil.isNullOrEmpty(gestor.getCargo())) {
            log.error("Cargo é obrigatório.");
            throw new RegraNegocioException("O cargo é obrigatório.");
        }

        if (StringUtil.isNullOrEmpty(gestor.getEmail()) ||
            !gestor.getEmail().contains("@")) {
            log.error("Email inválido.");
            throw new RegraNegocioException("Informe um e-mail válido.");
        }

        if (StringUtil.isNullOrEmpty(gestor.getTelefone())) {
            log.error("Telefone é obrigatório.");
            throw new RegraNegocioException("O telefone é obrigatório.");
        }
        
        if (!StringUtil.isCpfOuCnpjValido(gestor.getCpf_cnpj())) {
            throw new RegraNegocioException("CPF ou CNPJ inválido.");
        }

        // Ainda precisa validar: (endereço, senha, etc.)
    }
}