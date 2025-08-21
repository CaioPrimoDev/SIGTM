/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.avaliacoes.controller;

import br.com.ifba.avaliacoes.entity.Avaliacao;
import java.util.List;

/**
 *
 * @author User
 */
public interface AvaliacaoIController {
    List<Avaliacao> findAllByPonto(Long pontoId);
    List<Avaliacao> getMelhoresByPonto(Long pontoId);
    List<Avaliacao> getPioresByPonto(Long pontoId);
    
    Avaliacao saveForPonto(Long pontoId, Avaliacao a);
    Avaliacao update(Long id, Avaliacao a);
    void delete(Long id);
    Avaliacao findById(Long id);
}
