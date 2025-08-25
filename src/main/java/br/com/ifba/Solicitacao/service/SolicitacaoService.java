/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.Solicitacao.service;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.Solicitacao.repository.SolicitacaoRepository;
import br.com.ifba.usuario.entity.Usuario;
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
public class SolicitacaoService implements SolicitacaoIService {

    private final SolicitacaoRepository repo;

    @Override
    public Solicitacao save(Solicitacao solicitacao) {
        validarSolicitacao(solicitacao);
        try {
            return repo.save(solicitacao);
        } catch (DataIntegrityViolationException e) {
            log.error("Violação de integridade ao salvar Solicitação: {}", solicitacao.getId(), e);
            throw new RegraNegocioException("Dados inválidos ou solicitação já existe.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao salvar Solicitação.", e);
            throw new RegraNegocioException("Erro ao salvar Solicitação.");
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("Tentativa de excluir Solicitação com ID inválido: {}", id);
            throw new RegraNegocioException("ID de Solicitação inválido.");
        }
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Solicitação não encontrada para exclusão (ID: {}).", id, e);
            throw new RegraNegocioException("Solicitação não encontrada para exclusão.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao excluir Solicitação.", e);
            throw new RegraNegocioException("Erro ao excluir Solicitação.");
        }
    }

    @Override
    public List<Solicitacao> findAll() {
        try {
            return repo.findAll();
        } catch (RuntimeException e) {
            log.error("Erro ao buscar todas as Solicitações.", e);
            throw new RegraNegocioException("Erro ao buscar Solicitações.");
        }
    }

    @Override
    public Solicitacao findById(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido fornecido para busca: {}", id);
            throw new RegraNegocioException("ID inválido para busca.");
        }
        try {
            Optional<Solicitacao> opt = repo.findById(id);
            if (opt.isEmpty()) {
                log.warn("Solicitação não encontrada para ID: {}", id);
                throw new RegraNegocioException("Solicitação não encontrada.");
            }
            return opt.get();
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao buscar Solicitação por ID.", e);
            throw new RegraNegocioException("Erro ao buscar Solicitação por ID.");
        }
    }
    
    @Override
    public Solicitacao findByUsuario(Usuario usuario) {
    if (usuario == null || usuario.getId() == null) {
        log.warn("Usuário inválido fornecido para busca de solicitação: {}", usuario);
        throw new RegraNegocioException("Usuário inválido para busca de solicitação.");
    }

    try {
        Solicitacao solicitacao = repo.findFirstByUsuario(usuario);
        if (solicitacao == null) {
            log.warn("Nenhuma solicitação encontrada para o usuário ID: {}", usuario.getId());
            throw new RegraNegocioException("Nenhuma solicitação encontrada para o usuário informado.");
        }
        return solicitacao;
    } catch (RuntimeException e) {
        log.error("Erro inesperado ao buscar Solicitação para o usuário ID: {}", usuario.getId(), e);
        throw new RegraNegocioException("Erro ao buscar Solicitação para o usuário.");
    }
}


    @Override
    public void validarSolicitacao(Solicitacao solicitacao) {
        if (solicitacao == null) {
            log.warn("Solicitação recebida é nula.");
            throw new RegraNegocioException("A Solicitação não pode ser nula.");
        }

        // Validação do CNPJ
        if (StringUtil.isNullOrEmpty(solicitacao.getCnpj())) {
            log.warn("CNPJ da Solicitação é nulo ou vazio.");
            throw new RegraNegocioException("O CNPJ é obrigatório.");
        }
       /* if (!StringUtil.isCnpjValido(solicitacao.getCnpj())) {
            log.warn("CNPJ inválido: {}", solicitacao.getCnpj());
            throw new RegraNegocioException("O CNPJ informado é inválido.");
        }*/

        // Validação do nomeEmpresa
        if (StringUtil.isNullOrEmpty(solicitacao.getNomeEmpresa())) {
            log.warn("Nome da empresa da Solicitação é nulo ou vazio.");
            throw new RegraNegocioException("O nome da empresa é obrigatório.");
        }
        if (!StringUtil.hasValidLength(solicitacao.getNomeEmpresa(), 3, 100)) {
            log.warn("Nome da empresa fora do tamanho permitido: '{}'", solicitacao.getNomeEmpresa());
            throw new RegraNegocioException("O nome da empresa deve ter entre 3 e 100 caracteres.");
        }

        // Validação do usuário associado
        if (solicitacao.getUsuario() == null) {
            log.warn("Solicitação sem usuário associado.");
            throw new RegraNegocioException("A solicitação deve estar associada a um usuário.");
        }

        // Pode-se validar também o estado do boolean solicitouParceria, se desejar
    }


}
