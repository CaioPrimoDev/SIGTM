/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.entity;

import br.com.ifba.infrastructure.entity.Pessoa;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
@Getter  @Setter
@NoArgsConstructor
public class Gestor extends Pessoa {
    @Column(nullable = false, unique = true)
    private String matricula;
    
    @Column(nullable = false)
    private String cargo;  
}
