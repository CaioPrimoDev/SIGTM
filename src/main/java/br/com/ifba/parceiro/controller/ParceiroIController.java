/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.parceiro.controller;

import br.com.ifba.parceiro.entity.Parceiro;
import java.util.List;

/**
 *
 * @author User
 */
public interface ParceiroIController {
    
    boolean save(Parceiro parceiro);
    void delete (Long id);
    List<Parceiro> findAll();
    List<Parceiro> findByNome(String nome);
    Parceiro findById(Long id);
}
