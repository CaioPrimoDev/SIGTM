/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.usuario.enums.Papel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author User
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Usuario extends PersistenceEntity {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String Senha_Hash;
    
    @Column(nullable = false)
    private String telefone;
    
    // Na hora que for transformar em Parceiro, é exigido CNPJ
    @Column(nullable = false)
    private String Cpf_Cnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Papel papel;

        protected Usuario(String nome, String email, String Senha_Hash, String telefone, String Cpf_Cnpj, Papel papel) {
        this.nome = nome;
        this.email = email;
        this.Senha_Hash = Senha_Hash;
        this.telefone = telefone;
        this.Cpf_Cnpj = Cpf_Cnpj;
        this.papel = papel;
    }
}
