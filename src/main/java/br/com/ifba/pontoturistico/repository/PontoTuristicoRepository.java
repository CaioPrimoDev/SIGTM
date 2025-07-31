/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pontoturistico.repository;

import br.com.ifba.pontoturistico.entity.PontoTuristico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juant
 */
@Repository
public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long>{
    
    
    public List<PontoTuristico> findByNomeStartingWithIgnoreCase(String nome);
    
    // verifica se tem nome e localização cadastradas em um mesmo cadastro
    boolean existsByNomeAndLocalizacao(String nome, String localizacao);
}
