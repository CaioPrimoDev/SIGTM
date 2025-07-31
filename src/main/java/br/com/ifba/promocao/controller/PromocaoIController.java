/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.controller;

import br.com.ifba.promocao.entity.Promocao;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface PromocaoIController {
    Promocao save(Promocao promocao);
    Promocao update(Promocao promocao);
    void delete(Promocao promocao);
    List<Promocao> findAll();
    Promocao findById(Long id);    
    List<Promocao> filterPromocoes(String termo, String tipo);
}
