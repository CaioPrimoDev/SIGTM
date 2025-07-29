/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.servidor.controller;

import br.com.ifba.servidor.entity.TipoServidor;
import br.com.ifba.servidor.service.TipoServidorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class TipoServidorController implements TipoServidorIController {

    @Autowired
    private TipoServidorService service;

    @Override
    public TipoServidor save(TipoServidor tipo) {
        return service.save(tipo);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<TipoServidor> findAll() {
        return service.findAll();
    }

    @Override
    public TipoServidor findById(Long id) {
        return service.findById(id);
    }
}