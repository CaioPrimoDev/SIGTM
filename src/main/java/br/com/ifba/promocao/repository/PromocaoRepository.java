/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.repository;

import br.com.ifba.promocao.entity.Promocao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joice
 */
@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
    // Define uma consulta JPQL personalizada
    @Query("SELECT p FROM Promocao p WHERE " + //Seleciona promoções (p) onde:
           "(:tipo = 'TODOS' OR p.tipo = :tipo) AND " + //O tipo é igual ao parâmetro OU o parâmetro tipo é 'TODOS'
           "(LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " + //E o termo de busca aparece no título OU na descrição
           "LOWER(p.descricao) LIKE LOWER(CONCAT('%', :termo, '%')))") // LOWER converte para minúsculas para busca case-insensitive, CONCAT adiciona % antes e depois do termo para busca parcial
    List<Promocao> filtrar(@Param("tipo") String tipo, @Param("termo") String termo);// // Declara o método que executa a consulta e retorna uma lista de Promocoes que atendem aos critérios
}


