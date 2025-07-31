/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.Promocao;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface PromocaoIService {
    Promocao save(Promocao promocao);
    Promocao update(Promocao promocao);
    void delete(Promocao promocao);
    List<Promocao> findAll();
    List<Promocao> filtrarPromocoes(String termo, String tipo);
    Promocao findById(Long id);
}