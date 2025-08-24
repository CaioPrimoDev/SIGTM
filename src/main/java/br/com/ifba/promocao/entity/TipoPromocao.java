/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@Table(name = "tipo_promocao")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TipoPromocao extends PersistenceEntity {
    
    // Campo obrigatório para título
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    // Campo obrigatório com limite de 500 caracteres
    @Column(name = "regra", nullable = false, length = 500)
    private String regra;
    
    // Campo obrigatório com limite de 1000 caracteres
    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;
    
    @ManyToOne // Relacionamento Muitos-para-Um com Usuário
    @JoinColumn(name = "id_usuario_cadastro", nullable = false)
    private Usuario UsuarioCadastro; 
    
    @ManyToOne
    @JoinColumn(name = "publico_alvo", nullable = false)
    private PublicoPromocao publicoAlvo;

}