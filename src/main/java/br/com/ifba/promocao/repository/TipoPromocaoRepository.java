/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.repository;

import br.com.ifba.promocao.entity.TipoPromocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joice
 */
@Repository
public interface TipoPromocaoRepository extends JpaRepository<TipoPromocao, Long> {
    TipoPromocao findByTitulo(String titulo);

    public boolean existsByTitulo(String nome);
}
