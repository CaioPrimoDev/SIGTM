/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.controller;

import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.promocao.service.TipoPromocaoIService;
import br.com.ifba.sessao.UsuarioSession;
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
    
    @Autowired
    private UsuarioSession usuarioSession;

    // Método para salvar um tipo de promoção
    @Override
    public TipoPromocao save (TipoPromocao tipoPromocao) {
        return tipoPromocaoService.save(tipoPromocao);
    }

    // Método para atualizar um tipo de promoção existente
    @Override
    public TipoPromocao update(TipoPromocao tipoPromocao) {
        return tipoPromocaoService.update(tipoPromocao);
    }
    
    // Método para deletar um tipo de promoção
    @Override
    public void delete(TipoPromocao tipoPromocao) {
        tipoPromocaoService.delete(tipoPromocao.getId());
    }

    // Busca um tipo de promoção pelo ID
    @Override
    public TipoPromocao getTipoPromocaoById(Long id) {
        return tipoPromocaoService.findById(id);
    }

    // Retorna todos os tipos de promoção cadastrados
    @Override
    public List<TipoPromocao> findAll() {
        return tipoPromocaoService.findAll();}
}
