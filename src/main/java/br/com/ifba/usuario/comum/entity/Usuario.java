/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.comum.entity;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.infrastructure.entity.Pessoa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    // Mapeamento inverso: Usuario não tem a FK, mapeado por "usuario" em Solicitacao
    @OneToOne(mappedBy = "usuario",fetch = FetchType.EAGER)
    private Solicitacao solicitacao;

    //GAMBIARRA PARA RODAR
    @Column(nullable = true, unique = true)
    private String matricula;
    
}
