/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.parceiro.entity;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.enums.Papel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author User
 */

@Entity
@Table(name="parceiro")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Parceiro extends Usuario {
    private String Segmento_empresarial;
    private Time horario_abertura;
    private Time horario_fechamento;
    private String status_solicitacao;

    public Parceiro(String Segmento_empresarial, Time horario_abertura, Time horario_fechamento, String status_solicitacao, String nome, String email, String email_secundario, String Senha_Hash, String telefone, String Cpf_Cnpj, Papel papel) {
        super(nome, email, email_secundario, Senha_Hash, telefone, Cpf_Cnpj, Papel.PARCEIRO);
        this.Segmento_empresarial = Segmento_empresarial;
        this.horario_abertura = horario_abertura;
        this.horario_fechamento = horario_fechamento;
        this.status_solicitacao = status_solicitacao;
    }   
}
