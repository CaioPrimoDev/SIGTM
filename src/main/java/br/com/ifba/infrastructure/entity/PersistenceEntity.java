/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */
@MappedSuperclass
@Getter
@Setter
public class PersistenceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long id;  
}
