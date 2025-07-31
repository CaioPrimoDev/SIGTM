/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.controller;

import br.com.ifba.promocao.entity.Promocao;
import br.com.ifba.promocao.service.PromocaoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Joice
 */
@Controller
public class PromocaoController implements PromocaoIController{
    
    @Autowired
    private PromocaoIService promocaoService;

    @Override
    public Promocao save(Promocao promocao){
        return promocaoService.save(promocao);
    }
    
    @Override
    public Promocao update(Promocao promocao){
        return promocaoService.update(promocao);
    }
    
    @Override
    public void delete(Promocao promocao){
        promocaoService.delete(promocao);
    }
    
    @Override
    public List<Promocao> findAll(){
        return promocaoService.findAll();
    }

    @Override
    public Promocao findById(Long id) {
        return promocaoService.findById(Long.MIN_VALUE);
    }

    @Override
    public List<Promocao> filterPromocoes(String termo, String tipo) {
        return promocaoService.filtrarPromocoes(termo, tipo);
    }
}