/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Optional só retorna um, a diferença é que ele ESPERA que exista um usuario OU NÃO, podendo lidar melhor com exceções
    Optional<Usuario> findByEmail(String email);

    // Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByCnpj(String cnpj);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    boolean existsByEmail(String email);

    boolean existsByCnpj(String cnpj);
    
    Optional<Usuario> findByMatricula(String matricula);

    boolean existsByMatricula(String matricula);
}
