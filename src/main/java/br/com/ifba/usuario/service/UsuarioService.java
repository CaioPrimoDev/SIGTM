/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.TipoUsuarioRepository;
import br.com.ifba.usuario.repository.UsuarioRepository;
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
public class UsuarioService {
    
        private final UsuarioRepository UserRepo;
        
        private final TipoUsuarioRepository TipoUserRepo;
        
        public boolean save(Usuario user) {
        validarUsuario(user);
        try {
            UserRepo.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            // Violação de constraints do banco (ex: unique ou not null)
            log.error("Violação de integridade ao salvar Usuário: {}", user.getNome(), e);
            throw new RegraNegocioException("Já existe um usuário com esse nome ou dados inválidos.");
        } catch (RuntimeException e) {
            // Falha inesperada
            log.error("Erro inesperado ao salvar Usuário.", e);
            throw new RegraNegocioException("Erro ao salvar Usuário.");
        }
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("Tentativa de excluir Usuário com ID inválido: {}", id);
            throw new RegraNegocioException("ID de Usuário inválido.");
        }

        try {
            UserRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // ID não encontrado no banco
            log.error("Tentativa de exclusão de Usuário inexistente (ID: {}).", id, e);
            throw new RegraNegocioException("Usuário não encontrado para exclusão.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao excluir Usuário.", e);
            throw new RegraNegocioException("Erro ao excluir Usuário.");
        }
    }

    public List<Usuario> findAll() {
        try {
            return UserRepo.findAll();
        } catch (RuntimeException e) {
            log.error("Erro ao buscar todos os Usuário.", e);
            throw new RegraNegocioException("Erro ao buscar todos os Usuário.");
        }
    }

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

    public List<Usuario> findByNomeContainingIgnoreCase(String nome) {
        if (!StringUtils.hasText(nome)) {
            return Collections.emptyList();
        }

        List<Usuario> resultado = UserRepo.findByNomeContainingIgnoreCase(nome);
        
        if (resultado.isEmpty()) {
            log.info("Nenhum usuário encontrado para o termo: {}", nome);
        }

        return resultado;
    }

     public List<Usuario> findBySolicitacaoTrue(){
         
      return UserRepo.findBySolicitacaoTrue();
     }
    
     public Optional<Usuario> findByCnpj(String cnpj){
     
     return UserRepo.findByCnpj(cnpj);
     }
     
    public List<Usuario> findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(String nome){
    
    return UserRepo.findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(nome);
    }
    
    private void validarUsuario(Usuario user) {
        if (user == null) {
            log.warn("Usuário recebido é nulo.");
            throw new RegraNegocioException("O Usuário não pode ser nulo.");
        }

        if (StringUtil.isNullOrEmpty(user.getNome())) {
            log.warn("Nome do Usuário é nulo ou vazio.");
            throw new RegraNegocioException("O nome do Usuário é obrigatório.");
        }

        if (!StringUtil.hasValidLength(user.getNome(), 3, 30)) {
            log.warn("Nome do Usuário fora do tamanho permitido: '{}'", user.getNome());
            throw new RegraNegocioException("O nome do Usuário deve ter entre 3 e 30 caracteres.");
        }
        
        // Validações para parceiro
        if (user.getTipo().getNome().contains("PARCEIRO")) {
            
            if(!StringUtil.isCnpjValido(user.getCnpj()) || StringUtil.isNullOrEmpty(user.getCnpj())) {
                log.warn("CNPJ vazio ou inválido");
                throw new RegraNegocioException("Um CNPJ válido é obrigatório");
            }
            
            if(StringUtil.isNullOrEmpty(user.getNomeEmpresa())) {
                log.warn("O nome da empresa vazio ou inválido");
                throw new RegraNegocioException("O nome da empresa é obrigatório");
            }
            
        }
        
        // Validações para gestor
        if(user.getTipo().getNome().contains("GESTOR")) {
            
            if (StringUtil.isNullOrEmpty(user.getMatricula())) {
                throw new RegraNegocioException("Cargo do Gestor é obrigatória.");
            }
            
            if (StringUtil.isNullOrEmpty(user.getCargo())) {
                throw new RegraNegocioException("Cargo do Gestor é obrigatória.");
            }
        }
    }
    
}
