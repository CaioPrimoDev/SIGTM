/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.parceiro.controller;

import br.com.ifba.parceiro.entity.Parceiro;
import br.com.ifba.parceiro.service.ParceiroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class ParceiroController implements ParceiroIController {
    
    @Autowired
    private ParceiroService service;

    @Override
    public boolean save(Parceiro parceiro) {
        return service.save(parceiro);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<Parceiro> findAll() {
        return service.findAll();
    }

    @Override
    public List<Parceiro> findByNome(String nome) {
        return service.findByNome(nome);
    }

    @Override
    public Parceiro findById(Long id) {
        return service.findById(id);
    }

    
}
