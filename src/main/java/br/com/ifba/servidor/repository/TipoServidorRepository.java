/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.servidor.repository;

import br.com.ifba.servidor.entity.TipoServidor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface TipoServidorRepository extends JpaRepository<TipoServidor, Long> {

    Optional<TipoServidor> findByNome(String nome);

    List<TipoServidor> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);
}
