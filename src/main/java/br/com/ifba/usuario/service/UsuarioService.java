    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import br.com.ifba.util.RegraNegocioException;
import br.com.ifba.util.StringUtil;
import java.util.Collections;
import java.util.List;
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
public class UsuarioService implements UsuarioIService {
    
        private final UsuarioRepository UserRepo;
        
        @Override
        public boolean save(Usuario user) {
        validarUsuario(user);
        try {
            UserRepo.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            // Violação de constraints do banco (ex: unique ou not null)
            log.error("Violação de integridade ao salvar Usuário: {}", user.getPessoa().getNome(), e);
            throw new RegraNegocioException("Já existe um usuário com esse email ou dados inválidos.");
        } catch (RuntimeException e) {
            // Falha inesperada
            log.error("Erro inesperado ao salvar Usuário.", e);
            throw new RegraNegocioException("Erro ao salvar Usuário.");
        }
    }

        @Override
        public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("Tentativa de excluir Usuário com ID inválido: {}", id);
            throw new RegraNegocioException("ID de Usuário inválido.");
        }

        try {
            UserRepo.deleteById(id);
            UserRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            // ID não encontrado no banco
            log.error("Tentativa de exclusão de Usuário inexistente (ID: {}).", id, e);
            throw new RegraNegocioException("Usuário não encontrado para exclusão.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao excluir Usuário.", e);
            throw new RegraNegocioException("Erro ao excluir Usuário.");
        }
    }

        @Override
        public List<Usuario> findAll() {
        try {
            return UserRepo.findAll();
        } catch (RuntimeException e) {
            log.error("Erro ao buscar todos os Usuário.", e);
            throw new RegraNegocioException("Erro ao buscar todos os Usuário.");
        }
    }

        @Override
        public Usuario findById(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido fornecido para busca: {}", id);
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return UserRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado para ID: {}", id);
                    return new RegraNegocioException("Usuário não encontrado.");
                });
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao buscar Usuário por ID.", e);
            throw new RegraNegocioException("Erro ao buscar Usuário por ID.");
        }
    }

        @Override
        public List<Usuario> findByNomeContainingIgnoreCase(String nome) {
        if (!StringUtils.hasText(nome)) {
            return Collections.emptyList();
        }

        List<Usuario> resultado = UserRepo.findByPessoaNomeContainingIgnoreCase(nome);
        
        if (resultado.isEmpty()) {
            log.info("Nenhum usuário encontrado para o termo: {}", nome);
        }

        return resultado;
    }

        @Override
        public List<Usuario> findBySolicitacaoSolicitouParceriaTrue() {
        log.info("Iniciando busca por usuários com solicitação TRUE...");
        List<Usuario> usuarios = null;
        try {
            usuarios = UserRepo.findBySolicitacaoSolicitouParceriaTrue();
            if (usuarios.isEmpty()) {
                log.warn("Nenhum usuário com solicitação TRUE foi encontrado.");
            } else {
                log.info("Busca por usuários com solicitação TRUE concluída. Encontrados {} usuários.", usuarios.size());
            }
        } catch (org.springframework.dao.DataAccessException e) {
            // Captura exceções de acesso a dados (SQL, JPA, Hibernate)
            log.error("Erro de acesso a dados ao buscar usuários com solicitação TRUE: {}", e.getMessage(), e);
            throw new RegraNegocioException("Erro ao buscar usuários com solicitação pendente. Tente novamente mais tarde.");
        } catch (Exception e) {
            // Captura outras exceções inesperadas
            log.error("Erro inesperado ao buscar usuários com solicitação TRUE: {}", e.getMessage(), e);
            throw new RegraNegocioException("Ocorreu um erro interno ao buscar usuários pendentes.");
        }
        return usuarios;
    }
     
        //localizar todos os parceiros
        @Override
        public List<Usuario> findByTipoNomeIgnoreCase(String nomeTipo) {
        try {
            log.info("Iniciando busca por tipo de usuário: {}", nomeTipo);
            
            // Verificação de entrada
            if (!StringUtils.hasText(nomeTipo)) {
                log.warn("Parâmetro 'nomeTipo' está vazio ou nulo");
                return Collections.emptyList();
            }

            List<Usuario> resultado = UserRepo.findByTipoNomeIgnoreCase(nomeTipo);
            
            // Verificação de resultado
            if (resultado.isEmpty()) {
                log.info("Nenhum usuário encontrado para o tipo: {}", nomeTipo);
            } else {
                log.info("Encontrados {} usuários do tipo: {}", resultado.size(), nomeTipo);
            }
            
            return resultado;
        } catch (Exception e) {
            log.error("Erro ao buscar usuários por tipo: {}", nomeTipo, e);
            return Collections.emptyList();
        }
    }

        //localizar parceiros por nome 
        @Override
        public List<Usuario> findByTipoNomeAndNomeContainingIgnoreCase(String tipoNome, String nome) {
            try {
                log.info("Buscando parceiros com tipo: {} e nome: {}", tipoNome, nome);

                // Verificação de entrada
                if (!StringUtils.hasText(tipoNome) || !StringUtils.hasText(nome)) {
                    log.warn("Parâmetros inválidos - tipoNome: {}, nome: {}", tipoNome, nome);
                    return Collections.emptyList();
                }

                List<Usuario> resultado = UserRepo.findByTipoNomeAndNomeContainingIgnoreCase(tipoNome, nome);

                // Verificação de resultado
                if (resultado.isEmpty()) {
                    log.info("Nenhum parceiro encontrado para nome: {}", nome);
                } else {
                    log.info("Encontrados {} parceiros para nome: {}", resultado.size(), nome);
                }

                return resultado;
            } catch (Exception e) {
                log.error("Erro ao buscar parceiros por nome: {}", nome, e);
                return Collections.emptyList();
            }
        }
     
        @Override
        public List<Usuario> findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(String nome) {
        log.info("Iniciando busca por usuários pelo nome '{}' com solicitação TRUE e ativo TRUE...", nome);

        // 1. Verificação de entrada
        if (nome == null || nome.trim().isEmpty()) {
            log.error("Nome fornecido para busca é nulo ou vazio.");
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio para a busca.");
        }

        List<Usuario> usuarios = null;
        try {
            usuarios = UserRepo.findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(nome);

            if (usuarios.isEmpty()) {
                log.warn("Nenhum usuário encontrado para o nome '{}' com solicitação TRUE e ativo TRUE.", nome);
            } else {
                log.info("Busca por nome '{}' concluída. Encontrados {} usuários com solicitação TRUE e ativo TRUE.", nome, usuarios.size());
            }
        } catch (org.springframework.dao.DataAccessException e) {
            // Captura exceções de acesso a dados (SQL, JPA, Hibernate)
            log.error("Erro de acesso a dados ao buscar usuários pelo nome '{}' com solicitação TRUE e ativo TRUE: {}", nome, e.getMessage(), e);
            throw new RegraNegocioException("Erro ao buscar usuários pelo nome. Tente novamente mais tarde.");
        } catch (Exception e) {
            // Captura outras exceções inesperadas
            log.error("Erro inesperado ao buscar usuários pelo nome '{}' com solicitação TRUE e ativo TRUE: {}", nome, e.getMessage(), e);
            throw new RegraNegocioException("Ocorreu um erro interno ao buscar usuários pelo nome.");
        }
        return usuarios;
    }
   
        @Override
        public void validarUsuario(Usuario user) {
        if (user == null) {
            log.warn("Usuário recebido é nulo.");
            throw new RegraNegocioException("O Usuário não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(user.getPessoa().getNome())) {
            log.warn("Nome do Usuário é nulo ou vazio.");
            throw new RegraNegocioException("O nome do Usuário é obrigatório.");
        }

        if (!StringUtil.hasValidLength(user.getPessoa().getNome(), 3, 30)) {
            log.warn("Nome do Usuário fora do tamanho permitido: '{}'", user.getPessoa().getNome());
            throw new RegraNegocioException("O nome do Usuário deve ter entre 3 e 30 caracteres.");
        }       
    }

    @Override
    public Usuario findByPessoaId(Long pessoaId) {
        return UserRepo.findByPessoaId(pessoaId);
    }
    
}
