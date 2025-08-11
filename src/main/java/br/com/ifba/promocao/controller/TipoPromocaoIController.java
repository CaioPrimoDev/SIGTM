/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.controller;

import br.com.ifba.promocao.entity.TipoPromocao;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface TipoPromocaoIController {
    
    
    TipoPromocao save(TipoPromocao tipoPromocao);
    TipoPromocao update(TipoPromocao tipoPromocao);
    void delete(TipoPromocao tipoPromocao);
    TipoPromocao getTipoPromocaoById(Long id);
    public List<TipoPromocao> findAll();
}
