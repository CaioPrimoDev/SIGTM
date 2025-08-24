  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.parceiro.entity;

import br.com.ifba.evento.entity.Evento;
import br.com.ifba.pessoa.entity.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author User
 */
@Entity
@NoArgsConstructor
@Getter  @Setter
@Table(name = "parceiro")
public class Parceiro extends Pessoa {
    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String nomeEmpresa;
    
    @OneToMany(mappedBy = "parceiro", fetch = FetchType.LAZY)
    private List<Evento> eventos;  

 }
