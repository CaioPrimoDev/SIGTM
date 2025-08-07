/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.central.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author User
 */

@Entity
//@Data
@Getter  @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orientacao extends PersistenceEntity {
    @Column(nullable = false)
    private String categoria; // Ex: "FAQ", "Transporte", etc.

    @Column(nullable = false, length = 2000)
    private String descricao;
}
