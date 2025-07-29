/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.servidor.controller;

import br.com.ifba.servidor.entity.Servidor;
import br.com.ifba.servidor.service.ServidorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class ServidorController implements ServidorIController {

    @Autowired
    private ServidorService service;

    @Override
    public Servidor save(Servidor servidor) {
        return service.save(servidor);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<Servidor> findAll() {
        return service.findAll();
    }

    @Override
    public List<Servidor> findByNome(String nome) {
        return service.findByNome(nome);
    }

    @Override
    public Servidor findById(Long id) {
        return service.findById(id);
    }
}
