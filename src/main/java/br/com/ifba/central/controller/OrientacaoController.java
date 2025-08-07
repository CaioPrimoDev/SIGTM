/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.central.controller;

import br.com.ifba.central.entity.Orientacao;
import br.com.ifba.central.service.OrientacaoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */ 

@Controller
public class OrientacaoController implements OrientacaoIController {
    
    @Autowired
    private OrientacaoService service;
    

    @Override
    public Orientacao save(Orientacao o) {
        return service.save(o);
    }

    @Override
    public List<Orientacao> findAll() {
        return service.findAll();
    }

    @Override
    public Optional<Orientacao> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Orientacao> findByCategoria(String categoria) {
        return service.findByCategoria(categoria);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public Orientacao update(Long id, Orientacao nova) {
        return service.update(id, nova);
    }
    
}
