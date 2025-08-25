/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class UsuarioController implements UsuarioIController {

    @Autowired
    private UsuarioIService service;

    @Override
    public boolean save(Usuario user) {
        return service.save(user);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<Usuario> findAll() {
        return service.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Usuario> findByNomeContainingIgnoreCase(String nome) {
        return service.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Usuario> findBySolicitacaoSolicitouParceriaTrue() {
        return service.findBySolicitacaoSolicitouParceriaTrue();
    }

    @Override
    public List<Usuario> findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(String nome) {
        return service.findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(nome);
    }

    @Override
    public  List<Usuario> findByTipoNomeIgnoreCase(String nome){
        return service.findByTipoNomeIgnoreCase(nome);
    }
    
    @Override
    public List<Usuario> findByTipoNomeAndNomeContainingIgnoreCase(String tipoNome, String nome){
        return service.findByTipoNomeAndNomeContainingIgnoreCase(tipoNome, nome);
    }
    
    @Override    
    public Usuario findByPessoaId(Long pessoaId) {
        return service.findByPessoaId(pessoaId);
    }


}
