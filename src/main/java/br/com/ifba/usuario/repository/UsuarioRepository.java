/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Optional só retorna um, a diferença é que ele ESPERA que exista um usuario OU NÃO, podendo lidar melhor com exceções
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByCnpj(String cnpj);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByCnpj(String cnpj);
}
