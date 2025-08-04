/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.promocao.repository.TipoPromocaoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joice
 */
@Service
public class TipoPromocaoService implements TipoPromocaoIService {

    @Autowired
    private TipoPromocaoRepository tipoPromocaoRepository;
    
    @Override
    public TipoPromocao save(TipoPromocao tipoPromocao) {
        return tipoPromocaoRepository.save(tipoPromocao);
    }
    
    @Override
    public TipoPromocao update(TipoPromocao tipoPromocao) {
        if (!tipoPromocaoRepository.existsById(tipoPromocao.getId())) {
            throw new EntityNotFoundException("Tipo de promoção não encontrado");
        }
        return tipoPromocaoRepository.save(tipoPromocao);
    }

    @Override
    public void delete(TipoPromocao tipoPromocao) {
           tipoPromocaoRepository.delete(tipoPromocao);
    }


    @Override
    public List<TipoPromocao> findAll() {
        return tipoPromocaoRepository.findAll();
    }

    @Override
    public TipoPromocao findById(Long id) {
        return (TipoPromocao) tipoPromocaoRepository.findAll();
    }

    // Lista fixa temporario para os tipos de promoção 
    private static final List<String> TIPOS_PADRAO = Arrays.asList(
        "PROMOÇÃO", 
        "CUPOM", 
        "PACOTE"
    );

    // Método executado após a inicialização do Spring
    @PostConstruct
    public void initTiposPromocao() {
        for (String nomeTipo : TIPOS_PADRAO) {
            if (!tipoPromocaoRepository.existsByNome(nomeTipo)) {
                TipoPromocao tipo = new TipoPromocao();
                tipo.setNome(nomeTipo);
                tipoPromocaoRepository.save(tipo);
            }
        }
    }

}
