/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.controller;

import br.com.ifba.promocao.entity.PublicoPromocao;
import br.com.ifba.promocao.service.PublicoPromocaoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Joice
 */
@Controller
public class PublicoPromocaoController implements PublicoPromocaoIController {
        
    // Injeção automática do serviço de públicos
    @Autowired
    private PublicoPromocaoIService publicoService;

    // Método para salvar um público
    @Override
    public PublicoPromocao save(PublicoPromocao publicoPromocao) {
        return publicoService.save(publicoPromocao);
    }

    // Método para atualizar um público existente
    @Override
    public PublicoPromocao update(Long id, PublicoPromocao publicoPromocao) {
        publicoPromocao.setId(id);
        return publicoService.update(publicoPromocao);
    }

    // Método para excluir um público
    @Override
    public void delete(Long id) {
        publicoService.delete(id);
    }

    // Método para buscar todos os públicos
    @Override
    public List<PublicoPromocao> findAll() {
        return publicoService.findAll();
    }

    // Método para buscar público por ID
    @Override
    public PublicoPromocao findById(Long id) {
        return publicoService.findById(id);
    }

}
