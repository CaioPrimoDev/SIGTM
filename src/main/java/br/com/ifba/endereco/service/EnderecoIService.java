/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.endereco.service;

import br.com.ifba.endereco.entity.Endereco;
import java.util.List;

/**
 *
 * @author juant
 */
public interface EnderecoIService {
    
    Endereco save(Endereco endereco);
    Endereco update(Endereco endereco);
    void deleteById(Long id);
    List<Endereco> findAll();
    Endereco findById(Long id);
    Endereco encontrarOuCriarEndereco(Endereco dadosEndereco);
}
