/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuariocomum.controller;

import br.com.ifba.usuariocomum.entity.UsuarioComum;
import java.util.List;

/**
 *
 * @author User
 */
public interface UsuarioComumIController {
    boolean save(UsuarioComum user);
    void delete (Long id);
    List<UsuarioComum> findAll();
    List<UsuarioComum> findByNome(String nome);
    UsuarioComum findById(Long id);
}
