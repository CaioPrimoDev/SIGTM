/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.evento.repository;

import br.com.ifba.evento.entity.Evento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Casa
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    List <Evento> findByCategoriaContainingIgnoreCase(String categoria);//procurar eventos pela categoria
    
    List <Evento> findByNomeContainingIgnoreCase(String eventoNome);//procurar pelo  nome do evento
}
