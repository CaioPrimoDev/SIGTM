/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.parceiro.entity;

import br.com.ifba.usuariocomum.entity.UsuarioComum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Time;
import java.time.LocalDateTime;
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
public class Parceiro extends UsuarioComum {
    private String segmento_empresarial;
    private LocalDateTime horario_abertura;
    private LocalDateTime horario_fechamento;
    private String status_solicitacao;

    public Parceiro(String segmento_empresarial, LocalDateTime horario_abertura
            , LocalDateTime horario_fechamento, String status_solicitacao
            , String cpf_cnpj, String nome, String endereco, String email
            , String telefone, String email_secundario, String senha_hash) 
    { 
        super(cpf_cnpj, nome, endereco, email, telefone, email_secundario, senha_hash);
        this.segmento_empresarial = segmento_empresarial;
        this.horario_abertura = horario_abertura;
        this.horario_fechamento = horario_fechamento;
        this.status_solicitacao = status_solicitacao;
    }
    
    
    
}
