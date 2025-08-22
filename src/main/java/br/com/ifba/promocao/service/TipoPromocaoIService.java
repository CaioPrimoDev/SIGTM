/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.TipoPromocao;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface TipoPromocaoIService {
    
    TipoPromocao save(TipoPromocao tipoPromocao);
    TipoPromocao update(TipoPromocao tipoPromocao);
    void delete(Long id);
    List<TipoPromocao> findAll();
    TipoPromocao findById(Long id);
}
