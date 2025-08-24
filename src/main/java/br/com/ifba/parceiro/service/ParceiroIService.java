/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.parceiro.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.parceiro.entity.Parceiro;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Casa
 */
public interface ParceiroIService {
  
    boolean save(Parceiro user);
    void delete(Long id);
    List<Parceiro> findAll();
    Parceiro findById(Long id); 
    List<Parceiro> findByNomeContainingIgnoreCase(String nome);
    Optional<Parceiro> findByCnpj(String cnpj);
    void validarParceiro(Parceiro user);
    Parceiro tornarParceiro(Usuario usuario, String cnpj, String nomeEmpresa);
    Usuario removerParceiria(Parceiro parceiro);
   
}

