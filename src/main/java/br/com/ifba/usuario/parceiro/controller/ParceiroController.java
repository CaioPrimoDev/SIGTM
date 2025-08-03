/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.parceiro.controller;

import br.com.ifba.usuario.comum.entity.Usuario;
import br.com.ifba.usuario.parceiro.entity.Parceiro;
import br.com.ifba.usuario.parceiro.service.ParceiroService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class ParceiroController implements ParceiroIController {
    
    @Autowired
    private ParceiroService service;

    @Override
    public boolean save(Parceiro user) {
        return service.save(user);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public List<Parceiro> findAll() {
        return service.findAll();
    }

    @Override
    public Parceiro findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Parceiro> findByNomeContainingIgnoreCase(String nome) {
        return service.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public Optional<Parceiro> findByCnpj(String cnpj) {
        return service.findByCnpj(cnpj);
    }

    @Override
    public  Parceiro tornarParceiro(Usuario usuario, String cnpj, String nomeEmpresa){
    
    return service.tornarParceiro(usuario,cnpj,nomeEmpresa);
    }
    
}
