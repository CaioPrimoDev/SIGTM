/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.comum.controller;

import br.com.ifba.usuario.comum.entity.TipoUsuario;
import br.com.ifba.usuario.comum.service.TipoUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class TipoUsuarioController implements TipoUsuarioIController {

    @Autowired
    private TipoUsuarioService service;

    @Override
    public boolean save(TipoUsuario tipoUsuario) {
        return service.save(tipoUsuario);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<TipoUsuario> findAll() {
        return service.findAll();
    }

    @Override
    public TipoUsuario findById(Long id) {
        return service.findById(id);
    }
    
    @Override
    public TipoUsuario findByNome(String nome) {
        return service.findByNome(nome);
    }

}
