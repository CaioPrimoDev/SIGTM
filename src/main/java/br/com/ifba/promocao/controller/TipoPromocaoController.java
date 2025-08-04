/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.controller;

import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.promocao.service.TipoPromocaoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Joice
 */
@Controller
public class TipoPromocaoController implements TipoPromocaoIController{
    
    @Autowired
    private TipoPromocaoIService tipoPromocaoService;

    @Override
    public TipoPromocao save (TipoPromocao tipoPromocao) {
        return tipoPromocaoService.save(tipoPromocao);
    }

    @Override
    public TipoPromocao update(TipoPromocao tipoPromocao) {
        return tipoPromocaoService.update(tipoPromocao);
    }

    @Override
    public void delete(TipoPromocao tipoPromocao) {
        tipoPromocaoService.delete(tipoPromocao);
    }

    @Override
    public List<TipoPromocao> getTodosTiposPromocao() {
        return tipoPromocaoService.findAll();
    }

    @Override
    public TipoPromocao getTipoPromocaoById(Long id) {
        return tipoPromocaoService.findById(id);
    }
}
