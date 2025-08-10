/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pontoturistico.entity;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.gestor.entity.Gestor;
import br.com.ifba.itemturistico.entity.ItemTuristico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    //@ManyToOne
    //@JoinColumn(name = "gestor_id", nullable = false) // Cria a coluna 'gestor_id'
    //private Gestor gestor; // A referência para o gestor que o cadastrou

    // Construtor manual garante a inicialização completa e correta do objeto
    public PontoTuristico(String nome, String descricao, Endereco endereco,
                          int nivelAcessibilidade, String horarioAbertura, String horarioFechamento) {
        super(nome, descricao, endereco, nivelAcessibilidade); 
        
        // responsaveis por dizer qual o horario de funcionamento
        this.horarioFechamento = horarioFechamento;
        this.horarioAbertura = horarioAbertura;
    }
}
