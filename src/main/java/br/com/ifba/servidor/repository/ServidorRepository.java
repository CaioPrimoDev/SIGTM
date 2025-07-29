/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.servidor.repository;

import br.com.ifba.servidor.entity.Servidor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ServidorRepository extends JpaRepository<Servidor, Long> {

    Optional<Servidor> findByMatricula(String matricula);

    List<Servidor> findByNomeContainingIgnoreCase(String nome);

    boolean existsByMatricula(String matricula);
}
