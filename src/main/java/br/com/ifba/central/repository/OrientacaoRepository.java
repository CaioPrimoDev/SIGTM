/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.central.repository;

import br.com.ifba.central.entity.Orientacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */

@Repository
public interface OrientacaoRepository extends JpaRepository<Orientacao, Long> {
    List<Orientacao> findByCategoriaIgnoreCase(String categoria);
}