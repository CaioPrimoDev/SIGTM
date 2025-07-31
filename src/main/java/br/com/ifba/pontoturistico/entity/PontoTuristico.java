/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pontoturistico.entity;

import br.com.ifba.itemturistico.entity.ItemTuristico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author juant
 */
@Entity
@Table(name = "pontos_turisticos")
@Getter
@Setter
@NoArgsConstructor 
public class PontoTuristico extends ItemTuristico {
    
    @Column(name = "horario_abertura")
    private String horarioAbertura;

    @Column(name = "horario_fechamento")
    private String horarioFechamento;

    // Construtor manual garante a inicialização completa e correta do objeto
    public PontoTuristico(String nome, String descricao, String localizacao,
                          int nivelAcessibilidade, String horarioAbertura, String horarioFechamento) {
        super(nome, descricao, localizacao, nivelAcessibilidade); 
        
        // responsaveis por dizer qual o horario de funcionamento
        this.horarioFechamento = horarioFechamento;
        this.horarioAbertura = horarioAbertura;
    }
}
