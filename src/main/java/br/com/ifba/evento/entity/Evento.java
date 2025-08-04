/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.evento.entity;

import br.com.ifba.itemturistico.entity.ItemTuristico;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Casa
 */

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Evento extends ItemTuristico {
    @Column(nullable = false)
    LocalDateTime dataHora;
    
    @Column(nullable = false)
    String publicoAlvo;
    
    @Column(nullable = false)
    String programacao;
    
    @Column(nullable = false)
    String categoria;
 
    @ManyToOne
    Parceiro parceiro;

 
     
}
