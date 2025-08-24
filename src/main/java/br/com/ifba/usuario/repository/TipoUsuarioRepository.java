/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.TipoUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    TipoUsuario findByNome(String nome);

    List<TipoUsuario> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);
}
