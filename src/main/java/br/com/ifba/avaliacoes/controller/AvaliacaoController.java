/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.avaliacoes.controller;

import br.com.ifba.avaliacoes.entity.Avaliacao;
import br.com.ifba.avaliacoes.service.AvaliacaoIService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */

@Controller
@RequiredArgsConstructor
public class AvaliacaoController implements AvaliacaoIController {

    private final AvaliacaoIService service;

    @Override
    public List<Avaliacao> findAllByPonto(Long pontoId) { 
        return service.findAllByPonto(pontoId); 
    }
    
    @Override
    public List<Avaliacao> getMelhoresByPonto(Long pontoId) { 
        return service.getMelhoresByPonto(pontoId); 
    }
    
    @Override
    public List<Avaliacao> getPioresByPonto(Long pontoId) { 
        return service.getPioresByPonto(pontoId); 
    }
    

    @Override
    public Avaliacao saveForPonto(Long pontoId, Avaliacao a) { 
        return service.saveForPonto(pontoId, a); 
    }
    
    @Override
    public Avaliacao update(Long id, Avaliacao a) { 
        return service.update(id, a); 
    }
    
    @Override
    public void delete(Long id) { 
        service.delete(id); 
    }
    
    @Override
    public Avaliacao findById(Long id) { 
        return service.findById(id); 
    }
}
