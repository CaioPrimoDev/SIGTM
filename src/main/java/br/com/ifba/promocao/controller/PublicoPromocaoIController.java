/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.controller;

import br.com.ifba.promocao.entity.PublicoPromocao;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface PublicoPromocaoIController {
    // Listar todos os públicos
    List<PublicoPromocao> findAll();

    // Buscar público por ID
    PublicoPromocao findById (Long id);

    // Criar novo público
    PublicoPromocao save(PublicoPromocao publicoPromocao);

    // Atualizar público existente
    PublicoPromocao update(Long id, PublicoPromocao publicoPromocao);

    // Deletar público
    void delete (Long id);

}
