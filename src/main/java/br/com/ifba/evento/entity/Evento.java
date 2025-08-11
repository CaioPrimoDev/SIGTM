
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.evento.entity;

import br.com.ifba.itemturistico.entity.ItemTuristico;
import br.com.ifba.itemturistico.entity.ItemTuristicoProvisorio;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Casa
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Evento extends ItemTuristicoProvisorio {
    @Column(nullable = false)
    LocalDateTime hora;
    
    @Column(nullable = false)
    LocalDate data;
    
    @Column(nullable = false)
    String publicoAlvo;
    
    @Column(nullable = false)
    String programacao;
    
    @Column(nullable = false)
    String categoria;
 
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "parceiro_id", nullable = false)
    private Parceiro parceiro;  // Atributo referenciado no mappedBy 
}
