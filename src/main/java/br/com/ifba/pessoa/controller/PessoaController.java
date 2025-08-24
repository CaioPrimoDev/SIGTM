/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.controller;

import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.pessoa.service.PessoaIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class PessoaController implements PessoaIController {
    
    @Autowired
    private PessoaIService service;

    @Override
    public boolean save(Pessoa pessoa) {
        return service.save(pessoa);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<Pessoa> findAll() {
        return service.findAll();
    }

    @Override
    public Pessoa findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Pessoa> findByNomeContainingIgnoreCase(String nome) {
        return service.findByNomeContainingIgnoreCase(nome);
    }
    
}
