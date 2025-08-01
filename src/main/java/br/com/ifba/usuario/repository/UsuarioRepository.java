/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.TipoUsuario;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    public List<Usuario> findBySolicitacaoTrue();//pesquisar por todos os solicitantes
    
    public List<Usuario> findByNomeContainingIgnoreCaseAndSolicitacaoTrueAndAtivoTrue(String nome);//para a função de pesquisa  de solicitantes
    
   /* @Query("SELECT u FROM Usuario u WHERE u.tipo.name = 'PARCEIRO'")
      List<Usuario> findParceirosPorNomeTipo();//RETORNA TODOS OS PARCEIOS*/
      
   /*@Query("SELECT u FROM Usuario u " +
           "WHERE u.tipo = :tipo " + 
           "AND LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Usuario> findByTipoAndNome(@Param("tipo") TipoUsuario tipo,@Param("nome") String nome);//PESQUISA O parceiro pelo nome*/
 
}
