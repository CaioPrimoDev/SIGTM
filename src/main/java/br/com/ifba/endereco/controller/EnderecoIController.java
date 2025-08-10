/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.endereco.controller;

import br.com.ifba.endereco.entity.Endereco;
import java.util.List;

/**
 *
 * @author juant
 */
public interface EnderecoIController {
    
    void save(Endereco endereco);
    void update(Endereco endereco);
    void deleteById(Long id);
    List<Endereco> findAll();
    Endereco findById(Long id);
}
