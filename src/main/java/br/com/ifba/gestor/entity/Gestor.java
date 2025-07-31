/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.entity;

import br.com.ifba.usuariocomum.entity.UsuarioComum;
import jakarta.persistence.Entity;
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
//@Table(name="gestor")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Gestor extends UsuarioComum {
    private String matricula_funcional;
    private String departamento;
    private String cargo;

    public Gestor(String matricula_funcional, String departamento, String cargo, String cpf_cnpj, String nome, String endereco, String email, String telefone, String email_secundario, String senha_hash) {
        super(cpf_cnpj, nome, endereco, email, telefone, email_secundario, senha_hash);
        this.matricula_funcional = matricula_funcional;
        this.departamento = departamento;
        this.cargo = cargo;
    }
    
}
