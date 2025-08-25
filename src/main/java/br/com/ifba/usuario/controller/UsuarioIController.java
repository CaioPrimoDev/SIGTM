/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Usuario;
import java.util.List;

/**
 *
 * @author User
 */
public interface UsuarioIController {
    boolean save(Usuario user);
    void delete(Long id);
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario findByPessoaId(Long pessoaId);
    List <Usuario> findByNomeContainingIgnoreCase(String nome);
    List<Usuario> findBySolicitacaoSolicitouParceriaTrue();//pesquisar por todos os solicitantes
    List<Usuario> findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(String nome);//para a função de pesquisa  de solicitantes
    List<Usuario> findByTipoNomeIgnoreCase(String nome);
    List<Usuario> findByTipoNomeAndNomeContainingIgnoreCase(String tipoNome, String nome);
   // List<Usuario> findParceirosByNome(@Param("nome") String nome);// pesquisar parceiro via metodo
}
