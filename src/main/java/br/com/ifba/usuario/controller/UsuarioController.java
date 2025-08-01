/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class UsuarioController implements UsuarioIController {

    @Autowired
    private UsuarioService service;

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
    public List<Usuario> findBySolicitacaoTrue() {
        return service.findBySolicitacaoTrue();
    }

    @Override
    public List<Usuario> findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(String nome) {
        return service.findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(nome);
    }

    @Override
    public Optional<Usuario> findByCnpj(String cnpj) {

        return service.findByCnpj(cnpj);
    }

   /* @Override
    public List<Usuario> findParceirosPorNomeTipo() {

        return service.findParceirosPorNomeTipo();
    }*/
/*
    @Override
    public List<Usuario> findParceirosByNome(@Param("nome") String nome) {

        return service.findParceirosByNome(nome);
    }*/
}
