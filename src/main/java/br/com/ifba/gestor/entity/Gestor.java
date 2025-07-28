/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestor.entity;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.enums.Papel;
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
@Table(name="gestor")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Gestor extends Usuario {
    private String matricula_funcional;
    private String departamento;
    private String cargo;

    public Gestor(String matricula_funcional, String departamento, String cargo, String nome, String email, String Senha_Hash, String telefone, String Cpf_Cnpj, Papel papel) {
        super(nome, email, Senha_Hash, telefone, Cpf_Cnpj, Papel.GESTOR);
        this.matricula_funcional = matricula_funcional;
        this.departamento = departamento;
        this.cargo = cargo;
    }

    
}
