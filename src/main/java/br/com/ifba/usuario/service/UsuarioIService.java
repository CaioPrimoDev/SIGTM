/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import java.util.List;


/**
 *
 * @author User
 */
public interface UsuarioIService {
    
    boolean save(Usuario user);
    void delete(Long id);
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario findByPessoaId(Long pessoaId);
    void validarUsuario(Usuario user);    
}
