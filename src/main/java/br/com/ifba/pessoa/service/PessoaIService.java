/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.pessoa.entity.Pessoa;
import java.util.List;

/**
 *
 * @author User
 */
public interface PessoaIService {
    boolean save(Pessoa pessoa);
    void delete(Long id);
    List<Pessoa> findAll();
    Pessoa findById(Long id);
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}
