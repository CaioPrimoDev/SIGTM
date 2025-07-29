/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.parceiro.entity;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.enums.Papel;
import br.com.ifba.usuariocomum.entity.UsuarioComum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Time;
import java.util.Set;
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
    // Herança + Agregação
    @OneToOne // indica relacionamento um-para-um
    @JoinColumn(name = "usuario_comum_id", nullable = false)// Equivalente de Column para chave estrangeira
    private UsuarioComum origem;
    
    @Column(nullable = false)
    private String nomeEmpresa;
    
    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @Column(nullable = false, unique = true)
    private String emailExclusivo;
    
    @Column(nullable = false, unique = true)
    private String senha;
    
    @Column(nullable = false)
    private boolean status;

    // Um novo login é gerado
    public Parceiro(UsuarioComum origem, String nomeEmpresa, String cnpj, String emailExclusivo, String senha, String nome, String email, String Senha_Hash, String telefone, String Cpf_Cnpj, Papel papel) {
        super(nome, email, Senha_Hash, telefone, Cpf_Cnpj, papel);
        this.origem = origem;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.emailExclusivo = emailExclusivo;
        this.senha = senha;
        importarDados(origem);
    }        
    
    // Dessa forma, podemos usar parceiro.getNome() e os outros, sem precisar de parceiro.getOrigem().getNome()
    private void importarDados(UsuarioComum uc) {
        this.setNome(uc.getNome());
        this.setTelefone(uc.getTelefone());
        this.setSenha_Hash(uc.getSenha_Hash());
        this.setCpf_Cnpj(uc.getCpf_Cnpj());
        this.setPapel(Papel.PARCEIRO);
        this.setStatus(true);
        this.origem = uc;
    }
}
