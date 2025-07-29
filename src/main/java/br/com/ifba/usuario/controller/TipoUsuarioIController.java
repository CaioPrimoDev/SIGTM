/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.TipoUsuario;
import java.util.List;

/**
 *
 * @author User
 */
public interface TipoUsuarioIController {

    boolean save(TipoUsuario tipoUsuario);
    void delete(Long id);
    List<TipoUsuario> findAll();
    TipoUsuario findById(Long id);
}
