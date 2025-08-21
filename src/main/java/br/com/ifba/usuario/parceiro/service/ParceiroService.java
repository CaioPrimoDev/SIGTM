/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.parceiro.service;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.usuario.comum.controller.UsuarioIController;
import br.com.ifba.usuario.comum.entity.TipoUsuario;
import br.com.ifba.usuario.comum.entity.Usuario;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
import br.com.ifba.usuario.parceiro.repository.ParceiroRepository;
import br.com.ifba.util.RegraNegocioException;
import br.com.ifba.util.StringUtil;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author User
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ParceiroService implements ParceiroIService {
    
    private final ParceiroRepository repo;
    
     private final UsuarioIController usuarioController;
     
    @Override
    public boolean save(Parceiro user) {
        validarParceiro(user);
        try {
            repo.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            // Violação de constraints do banco (ex: unique ou not null)
            log.error("Violação de integridade ao salvar Parceiro: {}", user.getNome(), e);
            throw new RegraNegocioException("Já existe um Parceiro com esse nome ou dados inválidos.");
        } catch (RuntimeException e) {
            // Falha inesperada
            log.error("Erro inesperado ao salvar Parceiro.", e);
            throw new RegraNegocioException("Erro ao salvar Parceiro.");
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("Tentativa de excluir Parceiro com ID inválido: {}", id);
            throw new RegraNegocioException("ID de Usuário inválido.");
        }

        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // ID não encontrado no banco
            log.error("Tentativa de exclusão de Parceiro inexistente (ID: {}).", id, e);
            throw new RegraNegocioException("Parceiro não encontrado para exclusão.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao excluir Parceiro.", e);
            throw new RegraNegocioException("Erro ao excluir Parceiro.");
        }
    }

    @Override
    public List<Parceiro> findAll() {
        try {
            return repo.findAll();
        } catch (RuntimeException e) {
            log.error("Erro ao buscar todos os Parceiro.", e);
            throw new RegraNegocioException("Erro ao buscar todos os Parceiro.");
        }
    }

    @Override
    public Parceiro findById(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido fornecido para busca: {}", id);
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return repo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Parceiro não encontrado para ID: {}", id);
                    return new RegraNegocioException("Usuário não encontrado.");
                });
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao buscar Parceiro por ID.", e);
            throw new RegraNegocioException("Erro ao buscar Parceiro por ID.");
        }
    }

    @Override
    public List<Parceiro> findByNomeContainingIgnoreCase(String nome) {
        if (!StringUtils.hasText(nome)) {
            return Collections.emptyList();
        }

        List<Parceiro> resultado = repo.findByNomeContainingIgnoreCase(nome);
        
        if (resultado.isEmpty()) {
            log.info("Nenhum Parceiro encontrado para o termo: {}", nome);
        }

        return resultado;
    }
    
    @Override
    public Optional<Parceiro> findByCnpj(String cnpj) {
        log.info("Iniciando busca por Parceiro com CNPJ: {}", cnpj);
        // 1. Verificação de entrada
        if (cnpj == null || cnpj.trim().isEmpty()) {
            log.error("CNPJ fornecido para busca é nulo ou vazio.");
            throw new IllegalArgumentException("O CNPJ não pode ser nulo ou vazio para a busca.");
        }

       
        Optional<Parceiro> parceiro = Optional.empty(); // Inicializa com Optional vazio
        try {
            parceiro = repo.findByCnpj(cnpj);

            if (parceiro.isPresent()) {
                log.info("Busca por CNPJ: {} concluída. Parceiro encontrado.", cnpj);
            } else {
                log.warn("Nenhum Parceiro encontrado para o CNPJ: {}", cnpj);
            }
        } catch (org.springframework.dao.DataAccessException e) {
            log.error("Erro de acesso a dados ao buscar Parceiro por CNPJ {}: {}", cnpj, e.getMessage(), e);
            throw new RegraNegocioException("Erro ao buscar usuário pelo CNPJ. Tente novamente mais tarde.");
        } catch (Exception e) {
            log.error("Erro inesperado ao buscar Parceiro por CNPJ {}: {}", cnpj, e.getMessage(), e);
            throw new RegraNegocioException("Ocorreu um erro interno ao buscar Parceiro pelo CNPJ.");
        }
        return parceiro;
    }
   
    @Override
    public void validarParceiro(Parceiro user) {
        if (user == null) {
            log.warn("Parceiro recebido é nulo.");
            throw new RegraNegocioException("O Parceiro não pode ser nulo.");
        }
            
            /*if(!StringUtil.isCnpjValido(user.getCnpj()) || StringUtil.isNullOrEmpty(user.getCnpj())) {
                log.warn("CNPJ vazio ou inválido");
                throw new RegraNegocioException("Um CNPJ válido é obrigatório");
            }*/
            
            if(StringUtil.isNullOrEmpty(user.getNomeEmpresa())) {
                log.warn("O nome da empresa vazio ou inválido");
                throw new RegraNegocioException("O nome da empresa é obrigatório");
            }
    }
    
@Override
@Transactional
public Parceiro tornarParceiro(Usuario usuario, String cnpj, String nomeEmpresa) {
 
    Parceiro parceiro = new Parceiro();
   
    TipoUsuario tipo = new TipoUsuario();
    tipo.setNome("PARCEIRO");
    tipo.setDescricao("");
    
    parceiro.setNome(usuario.getNome());
    parceiro.setEmail(usuario.getEmail());
    parceiro.setTelefone(usuario.getTelefone());
    parceiro.setSenha(usuario.getSenha());
    parceiro.setAtivo(usuario.isAtivo());
    parceiro.setTipo(tipo);
   
    parceiro.setCnpj(cnpj);
    parceiro.setNomeEmpresa(nomeEmpresa);

    Solicitacao novaSolicitacao = new Solicitacao();
    novaSolicitacao.setCnpj(cnpj);
    novaSolicitacao.setNomeEmpresa(nomeEmpresa);
    novaSolicitacao.setSolicitouParceria(false);
    parceiro.setSolicitacao(novaSolicitacao);

    usuarioController.delete(usuario.getId());
    
    return parceiro;
}
    @Override
    public Usuario removerParceiria(Parceiro parceiro) {

        Usuario usuario = new Usuario();

        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("USUARIO_COMUM");
        tipo.setDescricao("");

        //DADOS DA PESSOA
        usuario.setNome(parceiro.getNome());
        usuario.setEmail(parceiro.getEmail());
        usuario.setTelefone(parceiro.getTelefone());

        // DADOS DO USUÁRIO
        usuario.setSenha(parceiro.getSenha());
        usuario.setAtivo(parceiro.isAtivo());
        usuario.setTipo(tipo);
        parceiro.getSolicitacao().setSolicitouParceria(false);

        return usuario;
    }
}
