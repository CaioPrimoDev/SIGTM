/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.servidor.controller;

import br.com.ifba.servidor.entity.TipoServidor;
import java.util.List;

/**
 *
 * @author User
 */
public interface TipoServidorIController {

    TipoServidor save(TipoServidor tipo);
    void delete(Long id);
    List<TipoServidor> findAll();
    TipoServidor findById(Long id);
}
