/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.controller;

import br.com.ifba.gestor.entity.Gestor;
import br.com.ifba.gestor.service.GestorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class GestorController implements GestorIController {
    
    @Autowired
    private GestorService service;

    @Override
    public boolean save(Gestor gestor) {
        return service.save(gestor);
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
    public List<Gestor> findByNome(String nome) {
        return service.findByNome(nome);
    }

    @Override
    public Gestor findById(Long id) {
        return service.findById(id);
    }
    
}
