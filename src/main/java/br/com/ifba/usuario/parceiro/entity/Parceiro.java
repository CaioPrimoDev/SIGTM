  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.parceiro.entity;

import br.com.ifba.evento.entity.Evento;
import br.com.ifba.usuario.comum.entity.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Getter  @Setter
@NoArgsConstructor
@Table(name = "parceiro")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Parceiro extends Usuario {
    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String nomeEmpresa;
    
    @OneToMany(mappedBy = "parceiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evento> eventos;  

 }
