/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.gestor.controller;

import br.com.ifba.gestor.entity.Gestor;
import java.util.List;

/**
 *
 * @author User
 */
public interface GestorIController {
    
    boolean save(Gestor user);
    void delete(Long id);
    List<Gestor> findAll();
    Gestor findById(Long id);
    List<Gestor> findByNomeContainingIgnoreCase(String nome);

}
