/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.itemturistico.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author juant
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ItemTuristico extends PersistenceEntity implements Serializable{
    
    @Column(name = "nome", nullable = false)
    protected String nome;

    // Usando 'TEXT' para descrições longas. 
    @Column(name = "descricao", columnDefinition = "TEXT")
    protected String descricao;

    @Column(name = "localizacao", nullable = false)
    protected String localizacao;
    
    @Column(name = "nivel_acessibilidade", nullable = false)
    protected int nivelAcessibilidade;
}
