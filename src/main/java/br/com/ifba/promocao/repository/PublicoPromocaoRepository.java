/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.repository;

import br.com.ifba.promocao.entity.PublicoPromocao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joice
 */
@Repository
public interface PublicoPromocaoRepository extends JpaRepository<PublicoPromocao, Long> {

    // Busca públicos pelo nome (exemplo de filtro customizado)
    List<PublicoPromocao> findByNomeContainingIgnoreCase(String nome);
}
