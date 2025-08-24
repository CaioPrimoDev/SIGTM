/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.evento.controller;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.evento.entity.Evento;
import br.com.ifba.evento.service.EventoService;
import br.com.ifba.parceiro.entity.Parceiro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
    void delete(Long id);
    List<Evento> findAll();
    Evento findById(Long id);
    List<Evento> findByCategoriaContainingIgnoreCase(String categoria);
    List<Evento> findByNomeContainingIgnoreCase(String eventoNome);*/


/**
 *
 * @author Casa
 */

@Controller
public class EventoController implements EventoIController {
   
    @Autowired
    EventoService service;
    
  @Override
  public  boolean save(Evento evento){
    return service.save(evento);
}
    
  @Override
  public  void delete(Long id){
     service.delete(id);
  }
  
  @Override
  public List <Evento> findAll(){
      return service.findAll();
  }
  
  @Override
 public Evento findById(Long id){
  return service.findById(id);
  }
  
  @Override
  public List<Evento> findByNomeContainingIgnoreCase(String eventoNome){
  return service.findByNomeContainingIgnoreCase(eventoNome);
          
  }
  
  @Override
 public List<Evento> findByCategoriaContainingIgnoreCase(String categoria){
  return service.findByCategoriaContainingIgnoreCase(categoria);
  }
 
  @Override
 public  Evento adicionarEvento(Evento evento,Parceiro parceiro,Endereco endereco){
 
 return service.adicionarEvento(evento, parceiro, endereco);
 }
 
}
