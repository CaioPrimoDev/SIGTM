/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.parceiro.service;

import br.com.ifba.parceiro.entity.Parceiro;
import br.com.ifba.parceiro.repository.ParceiroRepository;
import br.com.ifba.util.RegraNegocioException;
import br.com.ifba.util.StringUtil;
import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.interfaces.Check;
import br.com.safeguard.types.ParametroTipo;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ParceiroService {

    private ParceiroRepository repo;

    @Transactional
    public boolean save(Parceiro parceiro) {
        validarParceiro(parceiro);
        try {
            repo.save(parceiro);
            return true;
        } catch (PersistenceException e) {
            log.error("Erro ao salvar parceiro no banco de dados.", e);
            throw new RegraNegocioException("Erro ao salvar parceiro no banco de dados.", e);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.error("ID de parceiro inválido para exclusão.");
            throw new RegraNegocioException("ID de parceiro inválido para exclusão.");
        }

        try {
            repo.deleteById(id);
        } catch (PersistenceException e) {
            log.error("Erro ao excluir parceiro do banco de dados.", e);
            throw new RegraNegocioException("Erro ao excluir parceiro do banco de dados.", e);
        }
    }

    @Transactional
    public List<Parceiro> findAll() {
        try {
            return repo.findAll();
        } catch (PersistenceException e) {
            log.error("Erro ao buscar todos os parceiros.", e);
            throw new RegraNegocioException("Erro ao buscar todos os parceiros.", e);
        }
    }

    @Transactional
    public List<Parceiro> findByNome(String nome) {
        if (StringUtil.isNullOrEmpty(nome)) {
            log.error("Nome inválido.");
            throw new RegraNegocioException("Informe um nome válido para a busca.");
        }

        try {
            return repo.findByNomeContainingIgnoreCase(nome);
        } catch (PersistenceException e) {
            log.error("Erro ao buscar parceiro por nome.", e);
            throw new RegraNegocioException("Erro ao buscar parceiro por nome.", e);
        }
    }

    @Transactional
    public Parceiro findById(Long id) {
        if (id == null || id <= 0) {
            log.error("ID inválido.");
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return repo.findById(id)
                    .orElseThrow(() -> new RegraNegocioException("Parceiro não encontrado."));
        } catch (PersistenceException e) {
            log.error("Erro ao buscar parceiro por ID.", e);
            throw new RegraNegocioException("Erro ao buscar parceiro por ID.", e);
        }
    }

    // Validação de regra de negócio
    private void validarParceiro(Parceiro parceiro) {

        Check check = new SafeguardCheck();//instacia para as checagens de validação específicas

        if (parceiro == null) {
            log.error("Parceiro é nulo.");
            throw new RegraNegocioException("O parceiro não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(parceiro.getNome())
                || !StringUtil.hasValidLength(parceiro.getNome(), 3, 100)) {
            log.error("Nome do parceiro inválido.");
            throw new RegraNegocioException("O nome do parceiro deve ter entre 3 e 100 caracteres.");
        }

        if (StringUtil.isNullOrEmpty(parceiro.getSegmento_empresarial())) {
            log.error("Segmento empresarial é obrigatório.");
            throw new RegraNegocioException("O segmento empresarial é obrigatório.");
        }

        if (parceiro.getHorario_abertura() == null || parceiro.getHorario_fechamento() == null) {
            log.error("Horários de funcionamento são obrigatórios.");
            throw new RegraNegocioException("Horário de abertura e fechamento devem ser informados.");
        }

        if (parceiro.getHorario_abertura().isAfter(parceiro.getHorario_fechamento())) {
            log.error("Horário de abertura posterior ao de fechamento.");
            throw new RegraNegocioException("O horário de abertura não pode ser depois do de fechamento.");
        }

        if (StringUtil.isNullOrEmpty(parceiro.getStatus_solicitacao())) {
            log.error("Status da solicitação é obrigatório.");
            throw new RegraNegocioException("O status da solicitação é obrigatório.");
        }

        if (StringUtil.isNullOrEmpty(parceiro.getEmail())
                || !parceiro.getEmail().contains("@")) {
            log.error("Email inválido.");
            throw new RegraNegocioException("Informe um e-mail válido.");
        }

        if (StringUtil.isNullOrEmpty(parceiro.getTelefone())) {
            log.error("Telefone é obrigatório.");
            throw new RegraNegocioException("O telefone é obrigatório.");
        }

        if (check.elementOf(parceiro.getTelefone(), ParametroTipo.TELEFONE).validate().hasError()) {// validade para fazer a verificação, haserror para verificar retornar um booleano se é inválido(true) ou válido(false)
            log.error("Telefone inválido");
            throw new RegraNegocioException("Telefone inválido.");

        }

        if (!StringUtil.isCpfOuCnpjValido(parceiro.getCpf_cnpj())) {
            throw new RegraNegocioException("CPF ou CNPJ inválido.");
        }

        // Ainda falta validar endereço e senha
    }

}
