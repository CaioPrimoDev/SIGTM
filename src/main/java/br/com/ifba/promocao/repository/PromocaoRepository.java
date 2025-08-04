/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.repository;

import br.com.ifba.promocao.entity.Promocao;
import br.com.ifba.promocao.entity.TipoPromocao;
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

    @Query("SELECT p FROM Promocao p WHERE " +
           "(:tipo IS NULL OR p.tipo = :tipo) AND " +
           "(LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(p.descricao) LIKE LOWER(CONCAT('%', :termo, '%')))")
    List<Promocao> filtrar(@Param("tipo") TipoPromocao tipo, @Param("termo") String termo);
}
