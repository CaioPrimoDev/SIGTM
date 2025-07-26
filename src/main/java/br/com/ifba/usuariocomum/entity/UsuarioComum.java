/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuariocomum.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.safeguard.constraint.annotations.Verify;// adição das bibliotecas para o uso do verify
import br.com.safeguard.types.ParametroTipo;
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
@Table(name="usuario_comum")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioComum extends PersistenceEntity {
    // Dados
    private final String papel = "COMUM";
    private String cpf_cnpj;
    private String nome;
    private String endereco;// Como endreço é atributo multivaorado lembrar de criar um classe para ele
    private String email;
    @Verify(ParametroTipo.TELEFONE)//mesmca coisa da linha 30 
    private String telefone;
    private String email_secundario;
    private String senha_hash;
    
    // Preferências
    private boolean pref_notif_email;
    private boolean pref_notif_sms;
    private boolean pref_notif_sistema;
    private boolean pref_receber_promocoes;
    private boolean pref_receber_alteracoes;
    
    // Permissões
    private boolean podeComentar;

    public UsuarioComum(String cpf_cnpj, String nome, String endereco, String email
            , String telefone, String email_secundario, String senha_hash) {
        this.cpf_cnpj = cpf_cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.email_secundario = email_secundario;
        this.senha_hash = senha_hash;
        this.pref_notif_email = true;
        this.pref_notif_sms = true;
        this.pref_notif_sistema = true;
        this.pref_receber_promocoes = true;
        this.pref_receber_alteracoes = true;
        this.podeComentar = true;
    }   
}
