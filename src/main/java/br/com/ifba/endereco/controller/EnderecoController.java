/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.endereco.controller;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.endereco.service.EnderecoIService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 *
 * @author juant
 */
@Controller
@RequiredArgsConstructor
public class EnderecoController implements EnderecoIController {
    
    private final EnderecoIService enderecoService;

    @Override
    public void save(Endereco endereco) {
        this.enderecoService.save(endereco);
    }

    @Override
    public void update(Endereco endereco) {
        this.enderecoService.update(endereco);
    }

    @Override
    public void deleteById(Long id) {
        this.enderecoService.deleteById(id);
    }

    @Override
    public List<Endereco> findAll() {
        return this.enderecoService.findAll();
    }

    @Override
    public Endereco findById(Long id) {
        return this.enderecoService.findById(id);
    }
}
