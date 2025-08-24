/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.entity;

import br.com.ifba.pessoa.entity.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
/**
 *
 * @author User
 */
@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "gestor")
public class Gestor extends Pessoa {
    
    @Column(nullable = false, unique = true)
    private String matricula;
    
    @Column(nullable = false)
    private String cargo;  
}
