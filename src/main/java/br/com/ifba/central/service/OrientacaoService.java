/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.central.service;

import br.com.ifba.central.entity.Orientacao;
import br.com.ifba.central.repository.OrientacaoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class OrientacaoService implements OrientacaoIService {
    
    @Autowired
    private OrientacaoRepository repo;

    @Override
    public Orientacao save(Orientacao orientacao) {
        return repo.save(orientacao);
    }

    @Override
    public List<Orientacao> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Orientacao> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Orientacao> findByCategoria(String categoria) {
        return repo.findByCategoriaIgnoreCase(categoria);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Orientacao update(Long id, Orientacao novaOrientacao) {
        return repo.findById(id).map(orig -> {
                    orig.setCategoria(novaOrientacao.getCategoria());
                    orig.setDescricao(novaOrientacao.getDescricao());
                    return repo.save(orig);
                }).orElseThrow(() -> new RuntimeException("Orientação não encontrada para atualização"));
    }    

}
    

