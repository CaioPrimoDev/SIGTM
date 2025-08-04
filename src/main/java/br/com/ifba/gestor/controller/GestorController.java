/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.controller;

import br.com.ifba.gestor.entity.Gestor;
import br.com.ifba.gestor.service.GestorIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class GestorController implements GestorIController {
    
    @Autowired
    private GestorIService service;

    @Override
    public boolean save(Gestor user) {
        return service.save(user);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<Gestor> findAll() {
        return service.findAll();
    }

    @Override
    public Gestor findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Gestor> findByNomeContainingIgnoreCase(String nome) {
        return service.findByNomeContainingIgnoreCase(nome);
    }
    
}
