/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.entity;

import br.com.ifba.usuario.comum.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
/**
 *
 * @author User
 */
@Getter  @Setter
@NoArgsConstructor
@Entity
@Table(name = "gestor")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Gestor extends Usuario {
    @Column(nullable = false, unique = true)
    private String matricula;
    
    @Column(nullable = false)
    private String cargo;  
}
