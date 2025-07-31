/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;
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
@Table(name="promocoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Promocao extends PersistenceEntity {

    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "regras")
    private String regras;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "dataInicio")
    private Date dataInicio;
    
    @Column(name = "dataTermino")
    private Date dataTermino;
    
    @Column(name = "tipo")
    private String tipo;
}
