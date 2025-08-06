/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.Solicitacao.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Casa
 */
@Getter
@Setter
@Table(name = "Solicitacao")
@Entity
@NoArgsConstructor
public class Solicitacao extends PersistenceEntity  {

    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String nomeEmpresa;
    @Column(nullable = false)
    private boolean solicitouParceria = false; // por padrão nenhum usuário fez solicita;cão
// essa classe precisa ser eager
}
