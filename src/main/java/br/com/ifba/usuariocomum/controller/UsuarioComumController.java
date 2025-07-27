/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuariocomum.controller;

import br.com.ifba.usuariocomum.entity.UsuarioComum;
import br.com.ifba.usuariocomum.service.UsuarioComumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class UsuarioComumController implements UsuarioComumIController {
    
    @Autowired
    private UsuarioComumService service;

    @Override
    public boolean save(UsuarioComum user) {
        return service.save(user);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<UsuarioComum> findAll() {
        return service.findAll();
    }

    @Override
    public List<UsuarioComum> findByNome(String nome) {
        return service.findByNome(nome);
    }

    @Override
    public UsuarioComum findById(Long id) {
        return service.findById(id);
    }
    
}
