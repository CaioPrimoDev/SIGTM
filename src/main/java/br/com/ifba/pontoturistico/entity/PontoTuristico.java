/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pontoturistico.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author juant
 */

@Entity // Diz que esta classe é uma entidade
// @Table(name = "pontos_turisticos") // Diz que esta classe é uma tabela
@Getter
@Setter
// usa apenas os campos da classe PontoTurististico para equals e hashCode e ignora os de PersistenceEntity
@EqualsAndHashCode(callSuper = false) 
@NoArgsConstructor
public class PontoTuristico extends PersistenceEntity implements Serializable{
    
}
