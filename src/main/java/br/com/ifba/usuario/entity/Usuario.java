/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.pessoa.entity.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Usuario: representa credencial/acesso. NÃO estende mais Pessoa.
 * Está associado 1:1 a uma Pessoa concreta (Gestor ou Parceiro).
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "usuario")
public class Usuario{
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    private TipoUsuario tipo;
    
    // Associa o usuario a uma Pessoa (Gestor ou Parceiro)
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    // Mapeamento inverso: Usuario não tem a FK, mapeado por "usuario" em Solicitacao
    @OneToOne(mappedBy = "usuario",fetch = FetchType.EAGER)
    private Solicitacao solicitacao;

    //GAMBIARRA PARA RODAR
    @Column(nullable = true, unique = true)
    private String matricula;
    
}
