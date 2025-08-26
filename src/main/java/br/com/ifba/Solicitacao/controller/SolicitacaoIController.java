/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.Solicitacao.controller;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface SolicitacaoIController {
    Solicitacao save(Solicitacao solicitacao);
    void delete(Long id);
    List<Solicitacao> findAll();
    Optional<Solicitacao> findByUsuario(Usuario usuario);
    Solicitacao findById(Long id);
    List<Solicitacao> findBySolicitouParceriaTrue();
    List<Solicitacao> findByNomeUsuarioComSolicitacaoAtiva(String nome);
}
