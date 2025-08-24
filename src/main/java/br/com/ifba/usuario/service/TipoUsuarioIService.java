/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.TipoUsuario;
import java.util.List;

/**
 *
 * @author User
 */
public interface TipoUsuarioIService {
    boolean save(TipoUsuario tipoUsuario);
    void delete(Long id);
    List<TipoUsuario> findAll();
    TipoUsuario findById(Long id);
    void validarTipoUsuario(TipoUsuario tipoUsuario);
    TipoUsuario findByNome(String nome);    
}
