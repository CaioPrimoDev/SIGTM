/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pontoturistico.service;

import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.sessao.UsuarioSession;
import java.util.List;

/**
 *
 * @author juant
 */
public interface PontoTuristicoIService {
    
    public void verificaGestor(UsuarioSession userLogado);
    public void save(PontoTuristico pontoTuristico);
    public void update(PontoTuristico pontoTuristico);
    public void delete(PontoTuristico pontoTuristico);
    public List<PontoTuristico> findAll();
    public PontoTuristico findById(Long id);
    public List<PontoTuristico> findByNomeStartingWithIgnoreCase(String nome);
}
