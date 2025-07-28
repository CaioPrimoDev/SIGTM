/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuariocomum.entity;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.enums.Papel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author User
 */
@Entity
@Table(name="usuario_comum")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioComum extends Usuario {

    private String endereco;
    
    // Define se a conta está ativa ou não (permite/impede o login)
    private boolean status;
    
    @Column(nullable = false)
    private boolean podeComentar;

    public UsuarioComum(String endereco, boolean status, boolean podeComentar, String nome, String email, String Senha_Hash, String telefone, String Cpf_Cnpj, Papel papel) {
        super(nome, email, Senha_Hash, telefone, Cpf_Cnpj, Papel.COMUM);
        this.endereco = endereco;
        this.status = status;
        this.podeComentar = podeComentar;
    }

        
}
