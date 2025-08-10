/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pontoturistico.repository;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juant
 */
@Repository
public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long>{
    
    List<PontoTuristico> findByNomeStartingWithIgnoreCase(String nome);

    // Método para o EnderecoService verificar se este endereço está em uso.
    boolean existsByEnderecoId(Long enderecoId);
    
    // Método para a validação de unicidade de endereço do PontoTuristicoService.
    Optional<PontoTuristico> findByEndereco(Endereco endereco);
}
