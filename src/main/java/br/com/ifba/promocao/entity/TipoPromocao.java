/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tipos_promocao")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TipoPromocao extends PersistenceEntity{
    
    @Column(name = "nome", nullable = false, unique = true)
    private String nome; // Ex: "PROMOÇÃO", "CUPOM", "PACOTE"
}
