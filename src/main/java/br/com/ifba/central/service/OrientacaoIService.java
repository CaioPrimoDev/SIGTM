/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.central.service;

import br.com.ifba.central.entity.Orientacao;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface OrientacaoIService {
    Orientacao save(Orientacao orientacao);
    List<Orientacao> findAll();
    Optional<Orientacao> findById(Long id);
    List<Orientacao> findByCategoria(String categoria);
    void delete(Long id);
    Orientacao update(Long id, Orientacao novaOrientacao);
}
