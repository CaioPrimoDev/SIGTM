/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.Solicitacao.controller;

import br.com.ifba.Solicitacao.entity.Solicitacao;
import br.com.ifba.Solicitacao.service.SolicitacaoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */

@Controller
public class SolicitacaoController implements SolicitacaoIController {
    
    @Autowired
    private SolicitacaoIService solicitacaoService;

    @Override
    public Solicitacao save(Solicitacao solicitacao) {
        return solicitacaoService.save(solicitacao);
    }

    @Override
    public void delete(Long id) {
        solicitacaoService.delete(id);
    }

    @Override
    public List<Solicitacao> findAll() {
        return solicitacaoService.findAll();
    }

    @Override
    public Solicitacao findById(Long id) {
        return solicitacaoService.findById(id);
    }
    
}
