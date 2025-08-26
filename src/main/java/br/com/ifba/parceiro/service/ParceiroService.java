/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.parceiro.service;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.Solicitacao.service.SolicitacaoIService;
import br.com.ifba.usuario.entity.TipoUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.parceiro.entity.Parceiro;
import br.com.ifba.parceiro.repository.ParceiroRepository;
import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.pessoa.service.PessoaIService;
import br.com.ifba.usuario.service.TipoUsuarioIService;
import br.com.ifba.usuario.service.UsuarioIService;
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
import org.springframework.util.StringUtils;

/**
 *
 * @author User
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ParceiroService implements ParceiroIService {
    
    private final ParceiroRepository parceiroRepository;
    
     private final UsuarioIService usuarioService;
     
     private final SolicitacaoIService solicitacaoService;
     
     private final TipoUsuarioIService tipoUsuarioService;
     
     private final PessoaIService pessoaService;
     
     // ParceiroService não pode chamar ele mesmo, se não ocorre um loop beam
     //private final ParceiroIService parceiroService;
     
    @Override
    public boolean save(Parceiro user) {
        validarParceiro(user);
        try {
            parceiroRepository.save(user);
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
            parceiroRepository.deleteById(id);
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
            return parceiroRepository.findAll();
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
            return parceiroRepository.findById(id)
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

        List<Parceiro> resultado = parceiroRepository.findByNomeContainingIgnoreCase(nome);
        
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
            parceiro = parceiroRepository.findByCnpj(cnpj);

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
    public Parceiro tornarParceiro(Usuario usuario, String cnpj, String nomeEmpresa) {
        if (usuario == null || usuario.getPessoa() == null) {
            throw new RegraNegocioException("Usuário ou dados pessoais inválidos para criar parceiro.");
        }

        // Dados base da pessoa
        Pessoa pessoaBase = usuario.getPessoa();

        TipoUsuario tipoParceiro = tipoUsuarioService.findByNome("PARCEIRO");

        // Criar Parceiro reutilizando o ID da Pessoa
        Parceiro parceiro = new Parceiro();
        parceiro.setId(pessoaBase.getId());
        parceiro.setNome(pessoaBase.getNome());
        parceiro.setTelefone(pessoaBase.getTelefone());
        parceiro.setCnpj(cnpj);
        parceiro.setNomeEmpresa(nomeEmpresa);

        // Atualizar vínculo do Usuario
        usuario.setPessoa(parceiro);
        usuario.setTipo(tipoParceiro);

        // Remover solicitação existente, se houver
        solicitacaoService.findByUsuario(usuario)
            .ifPresent(slc -> solicitacaoService.delete(slc.getId()));

        // Persistir Parceiro e Usuario diretamente pelo Repository
        Parceiro parceiroPersistido = parceiroRepository.save(parceiro);
        usuarioService.save(usuario);

        return parceiroPersistido;
    }

    @Override
    public Usuario removerParceiria(Parceiro parceiro) {
        if (parceiro == null) {
            throw new RegraNegocioException("Parceiro inválido.");
        }

        // Buscar tipo de usuário comum
        TipoUsuario tipoComum = tipoUsuarioService.findByNome("USUARIO_COMUM");

        // Buscar o usuário associado ao Parceiro
        Usuario usuario = usuarioService.findByPessoaId(parceiro.getId());
        if (usuario == null) {
            throw new RegraNegocioException("Usuário não encontrado para o parceiro informado.");
        }

        // Criar nova Pessoa
        Pessoa pessoaAntiga = new Pessoa();
        pessoaAntiga.setNome(parceiro.getNome());
        pessoaAntiga.setTelefone(parceiro.getTelefone());

        // Persistir nova Pessoa (não usamos retorno)
        boolean pessoaSalva = pessoaService.save(pessoaAntiga);
        if (!pessoaSalva) {
            throw new RegraNegocioException("Erro ao salvar dados da pessoa.");
        }

        // Atualizar Usuario
        usuario.setTipo(tipoComum);
        usuario.setPessoa(pessoaAntiga);
        boolean usuarioSalvo = usuarioService.save(usuario);
        if (!usuarioSalvo) {
            throw new RegraNegocioException("Erro ao atualizar usuário.");
        }

        // Remover solicitação se existir
        solicitacaoService.findByUsuario(usuario)
                          .ifPresent(s -> solicitacaoService.delete(s.getId()));

        // Remover o Parceiro antigo
        this.delete(parceiro.getId());

        return usuario;
    }
                   

}
