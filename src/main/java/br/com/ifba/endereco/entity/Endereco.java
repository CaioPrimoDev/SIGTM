/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.endereco.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author juant
 */
@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class Endereco extends PersistenceEntity{
    
    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "cidade", nullable = false)
    private String cidade;
    
    @Column(name = "bairro", nullable = false)
    private String bairro;
    
    @Column(name = "rua", nullable = false)
    private String rua;
    
    @Column(name = "numero", nullable = true)
    private String numero;

    @Override
    public String toString() {
        // Usando String.format para uma formatação limpa.
        // O '\n' cria uma quebra de linha, ideal para caixas de diálogo.
        return String.format(
            "Estado: %s\n" +
            "Cidade: %s\n" +
            "Bairro: %s\n" +
            "Rua: %s\n" +
            "Número: %s",
            this.estado,
            this.cidade,
            this.bairro,
            (this.rua != null && !this.rua.isEmpty()) ? this.rua : "Não informado",
            (this.numero != null && !this.numero.isEmpty()) ? this.numero : "S/N"
        );
    }    
}
