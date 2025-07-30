/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import br.com.ifba.infrastructure.entity.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author User
 */
@Entity
@Getter  @Setter
public class Usuario extends Pessoa {

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne(optional = false)
    private TipoUsuario tipo;
    
    // Verifica para aparecer na tabela de solicitações para parceria
    @Column(nullable = false)
    private boolean socilitacao;

    // Atributos específicos de parceiro (opcionais)
    @Column(nullable = true, unique = true)
    private String cnpj;

    @Column(nullable = true)
    private String nomeEmpresa;
    
    // Atributos específicos de gestor (opcionais)
    @Column(nullable = true, unique = true)
    private String matricula;
    
    @Column(nullable = true)
    private String cargo;

    public Usuario(String senha, TipoUsuario tipo) {
        this.senha = senha;
        this.status = true;
        this.tipo = tipo;
        this.socilitacao = false;
        this.cnpj = null;
        this.nomeEmpresa = null;
        this.matricula = null;
        this.cargo = null;
    }

    
    
    
}
