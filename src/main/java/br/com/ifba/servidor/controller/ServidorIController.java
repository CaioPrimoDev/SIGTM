/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.servidor.controller;

import br.com.ifba.servidor.entity.Servidor;
import java.util.List;

/**
 *
 * @author User
 */
public interface ServidorIController {

    Servidor save(Servidor servidor);
    void delete(Long id);
    List<Servidor> findAll();
    List<Servidor> findByNome(String nome);
    Servidor findById(Long id);
}
