/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.gestor.repository;

import br.com.ifba.gestor.entity.Gestor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {
    // Método para busca por nome com LIKE
    List<Gestor> findByNomeContainingIgnoreCase(String termo); 
}
