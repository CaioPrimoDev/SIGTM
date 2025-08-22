/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.evento.service;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.evento.entity.Evento;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
import java.util.List;

/**
 *
 * @author Casa
 */
public interface EventoIService {
 
    boolean save(Evento evento);

    void delete(Long id);

    List<Evento> findAll();

    Evento findById(Long id);

    List<Evento> findByCategoriaContainingIgnoreCase(String categoria);

    List<Evento> findByNomeContainingIgnoreCase(String eventoNome);

    void validarEvento(Evento evento);
    
    Evento adicionarEvento(Evento evento,Parceiro parceiro,Endereco endereco);
}
