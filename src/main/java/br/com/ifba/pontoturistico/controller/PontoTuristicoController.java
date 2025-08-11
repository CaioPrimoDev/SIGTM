/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pontoturistico.controller;

import br.com.ifba.usuario.gestor.entity.Gestor;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.pontoturistico.service.PontoTuristicoIService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 *
 * @author juant
 */
@Controller
@RequiredArgsConstructor
public class PontoTuristicoController implements PontoTuristicoIController {
    
    private final PontoTuristicoIService pontoTuristicoService;

    @Override
    public void save(PontoTuristico pontoTuristico) {
        pontoTuristicoService.save(pontoTuristico);
    }

    @Override
    public void update(PontoTuristico pontoTuristico) {
        pontoTuristicoService.update(pontoTuristico);
    }

    @Override
    public void delete(PontoTuristico pontoTuristico) {
        pontoTuristicoService.delete(pontoTuristico);
    }

    @Override
    public List<PontoTuristico> findAll() {
        return pontoTuristicoService.findAll();
    }

    @Override
    public PontoTuristico findById(Long id) {
        return pontoTuristicoService.findById(id);
    }

    @Override
    public List<PontoTuristico> findByNomeStartingWithIgnoreCase(String nome) {
        return pontoTuristicoService.findByNomeStartingWithIgnoreCase(nome);
    }
}
