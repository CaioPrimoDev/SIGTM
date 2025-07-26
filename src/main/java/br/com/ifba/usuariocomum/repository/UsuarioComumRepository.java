/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuariocomum.repository;

import br.com.ifba.usuariocomum.entity.UsuarioComum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface UsuarioComumRepository extends JpaRepository<UsuarioComum, Long> {
    // Método para busca por nome com LIKE
    List<UsuarioComum> findByNomeContainingIgnoreCase(String termo);
}
