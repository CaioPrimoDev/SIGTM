/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.Solicitacao.service;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface SolicitacaoIService {
    Solicitacao save(Solicitacao solicitacao);
    void delete(Long id);
    List<Solicitacao> findAll();
    Solicitacao findById(Long id);
    Optional<Solicitacao> findByUsuario(Usuario usuario);
    void validarSolicitacao(Solicitacao solicitacao);
    List<Solicitacao> findBySolicitouParceriaTrue();
    List<Solicitacao> findByNomeUsuarioComSolicitacaoAtiva(String nome);
}
