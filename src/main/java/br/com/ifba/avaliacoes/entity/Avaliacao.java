/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.avaliacoes.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author User
 */

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao extends PersistenceEntity {

    @NotBlank
    @Column(nullable = false)
    private String nomeAutor;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private int estrelas;

    @Column(nullable = true, length = 2000)
    private String descricao;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) // Talvés eu tenha que mudar para EAGER
    @JoinColumn(name = "ponto_turistico_id", nullable = false)
    private PontoTuristico pontoTuristico;
    
}
