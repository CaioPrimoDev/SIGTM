/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.PublicoPromocao;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface PublicoPromocaoIService {
    
    PublicoPromocao save(PublicoPromocao publicoPromocao);

    PublicoPromocao update(PublicoPromocao publicoPromocao);

    void delete(Long id);

    List<PublicoPromocao> findAll();

    PublicoPromocao findById(Long id);
}
