/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.Solicitacao.controller;

import br.com.ifba.usuario.comum.entity.Usuario;
import br.com.ifba.usuario.comum.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */

@Controller
public class SolicitacaoController implements SolicitacaoIController {
    
    @Autowired
    private UsuarioService service;

    @Override
    public void solicitarParceria(Usuario usuario, String cnpj, String nomeEmpresa) {
        service.processarSolicitacaoParceria(usuario, cnpj, nomeEmpresa);
    }
    
}
