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
    
    // Injeção automática do serviço de promoções
    @Autowired
    private PromocaoIService promocaoService;

    //método para salvar uma promoção
    @Override
    public Promocao save(Promocao promocao){
        return promocaoService.save(promocao);
    }
    
    //método para atualizar uma promoção existente
    @Override
    public Promocao update(Promocao promocao){
        return promocaoService.update(promocao);
    }
    
    //método para excluir uma promoção
    @Override
    public void delete(Promocao promocao){
        promocaoService.delete(promocao);
    }
    
    //método para buscar todas as promoções
    @Override
    public List<Promocao> findAll(){
        return promocaoService.findAll();
    }

    //método para buscar por id
    @Override
    public Promocao findById(Long id) {
        return promocaoService.findById(id);
    }

    //método para filtrar promoções
    @Override
    public List<Promocao> filterPromocoes(String termo, String tipo) {
        return promocaoService.filtrarPromocoes(termo, tipo);
    }
}