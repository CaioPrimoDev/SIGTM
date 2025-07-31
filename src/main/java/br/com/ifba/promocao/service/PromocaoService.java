/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.Promocao;
import br.com.ifba.promocao.repository.PromocaoRepository;
import br.com.ifba.util.StringUtil;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joice
 */
@Service
public class PromocaoService implements PromocaoIService {
    
    @Autowired
    private PromocaoRepository promocaoRepository;
     
    private static final Logger log = LoggerFactory.
                            getLogger(PromocaoService.class);
       
    @Override
    public Promocao save(Promocao promocao) {
        log.info("Salvando promoção: {}", promocao.getTitulo());
        validatePromocao(promocao);
        return promocaoRepository.save(promocao);
    }

    @Override
    public Promocao update(Promocao promocao) {
        log.info("Atualizando promoção ID {}: {}", promocao.getId(), promocao.getTitulo());
        validatePromocao(promocao);
        return promocaoRepository.save(promocao);
    }

    @Override
    public void delete(Promocao promocao) {
        log.info("Removendo promoção ID {}: {}", promocao.getId(), promocao.getTitulo());

        String mensagem = String.format("Deseja realmente apagar a promoção %s - %s?", 
                                      promocao.getTipo(), 
                                      promocao.getTitulo());

        int confirmacao = JOptionPane.showConfirmDialog(
            null, 
            mensagem, 
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            promocaoRepository.delete(promocao);
            JOptionPane.showMessageDialog(null, "Promoção excluída com sucesso!");
        }
    }

    @Override
    public List<Promocao> findAll() {
        log.info("Listando todas as promoções");
        return promocaoRepository.findAll();
    }
    
    @Override
    public Promocao findById(Long id) {
        log.info("Buscando promoção por ID: {}", id);
        return promocaoRepository.findById(id).orElse(null);
    }
    
    public List<Promocao> filtrarPromocoes(String termo, String tipo) {
        return promocaoRepository.filtrar(
            tipo == null || tipo.equals("TODOS") ? "TODOS" : tipo,
            termo == null ? "" : termo
        );
    }

    // Validações adicionais
    public void validatePromocao(Promocao promocao) {
        if (promocao == null) {
            throw new IllegalArgumentException("Preencha todos os campos");
        }
        
        validateTitulo(promocao.getTitulo());
        validateDescricao(promocao.getDescricao());
        validateDatas(promocao.getDataInicio(), promocao.getDataTermino());
        validateRegras(promocao.getRegras());
    }
    
    private void validateTitulo(String titulo) {
        if (StringUtil.isNullOrEmpty(titulo)) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        if (!StringUtil.hasValidLength(titulo, 3, 20)) {
            throw new IllegalArgumentException("Título deve ter entre 3 e 20 caracteres");
        }
    }
    
    private void validateDescricao(String descricao) {
        if (StringUtil.isNullOrEmpty(descricao)) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }
        if (!StringUtil.hasValidLength(descricao, 10, 100)) {
            throw new IllegalArgumentException("Descrição deve ter entre 10 e 100 caracteres");
        }
    }
    
    private void validateDatas(Date dataInicio, Date dataTermino) {
        if (dataInicio == null || dataTermino == null) {
            throw new IllegalArgumentException("Datas de início e término são obrigatórias");
        }
        
        if (dataTermino.before(dataInicio)) {
            throw new IllegalArgumentException("Data de término deve ser após a data de início");
        }
    }
    
    private void validateRegras(String regras) {
        if (!StringUtil.isNullOrEmpty(regras) && !StringUtil.hasValidLength(regras, 0, 100)) {
            throw new IllegalArgumentException("Regras não podem exceder 100 caracteres");
        }
    }
}