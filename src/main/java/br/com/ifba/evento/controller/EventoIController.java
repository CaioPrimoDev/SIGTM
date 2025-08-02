/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.evento.controller;

import br.com.ifba.evento.entity.Evento;
import br.com.ifba.evento.service.EventoService;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Casa
 */

public interface EventoIController {
   
    boolean save(Evento evento);
    void delete(Long id);
    List<Evento> findAll();
    Evento findById(Long id);
    List<Evento> findByCategoriaContainingIgnoreCase(String categoria);
    List<Evento> findByNomeContainingIgnoreCase(String eventoNome);
}
