/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.parceiro.repository;

import br.com.ifba.parceiro.entity.Parceiro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
    // Método para busca por nome com LIKE
    List<Parceiro> findByNomeContainingIgnoreCase(String termo);
}
