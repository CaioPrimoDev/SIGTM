/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.avaliacoes.service;

import br.com.ifba.avaliacoes.entity.Avaliacao;
import java.util.List;

/**
 *
 * @author User
 */
public interface AvaliacaoIService {
    List<Avaliacao> findAllByPonto(Long pontoId);
    List<Avaliacao> getMelhoresByPonto(Long pontoId);
    List<Avaliacao> getPioresByPonto(Long pontoId);

    Avaliacao findById(Long id);
    Avaliacao saveForPonto(Long pontoId, Avaliacao avaliacao);
    Avaliacao update(Long id, Avaliacao avaliacao);
    void delete(Long id);
}
