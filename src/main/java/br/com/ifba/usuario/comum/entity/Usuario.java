/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.comum.entity;

import br.com.ifba.infrastructure.entity.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author User
 */
@Entity
@NoArgsConstructor
@Getter  @Setter
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED) // Separa as tabelas herdadas
public class Usuario extends Pessoa {

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    private TipoUsuario tipo;
    
    // Verifica para aparecer na tabela de solicitações para parceria
    @Column(nullable = false)
    private boolean solicitacao;
    
    //GAMBIARRA PARA RODAR
    @Column(nullable = true, unique = true)
    private String matricula;
    
}
