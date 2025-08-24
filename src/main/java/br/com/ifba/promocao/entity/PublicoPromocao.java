/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.entity;

import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Joice
 */
@Entity //marca a classe como uma entidade JPA (será mapeada para tabela no banco)
@Table(name="publico_alvo") // define o nome da tabela no banco como "promocoes"
@NoArgsConstructor // cria um construtor sem argumentos
@AllArgsConstructor //cria um construtor com todos os argumentos
@Getter //cria os métos getters
@Setter //cria os métosdo setters
@ToString // gera o método toString() para a classe
public class PublicoPromocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo obrigatório para título
    @Column(name = "nome", nullable = false)
    private String nome;    
    
    // Campo obrigatório com limite de 1000 caracteres
    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;
    
    @Column(name = "faixa_etaria", nullable = false)
    private String faixaEtaria;
    
    @Column(name = "interesse", nullable = false)
    private String interesse;
    
    @ManyToOne // Relacionamento Muitos-para-Um com Usuário
    @JoinColumn(name = "id_usuario_cadastro", nullable = false)
    private Usuario UsuarioCadastro; 
}
