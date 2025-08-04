/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.Promocao;
import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.promocao.repository.PromocaoRepository;
import br.com.ifba.promocao.repository.TipoPromocaoRepository;
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
    
    // Permite acesso ao banco de dados sem instanciação manual
    @Autowired
    private PromocaoRepository promocaoRepository;
    
    
    @Autowired
    private TipoPromocaoRepository tipoPromocaoRepository;
     
    // Cria um logger para registrar atividades da classe
    private static final Logger log = LoggerFactory.
                            getLogger(PromocaoService.class);
       
    // Salva uma nova promoção/cupom/pacote
    @Override
    public Promocao save(Promocao promocao) {
        log.info("Salvando {}", promocao.getTitulo());
        validatePromocao(promocao); //Valida os dados
        return promocaoRepository.save(promocao); //Repassa para o repositório salvar no banco
    }

    // Atualiza uma promoção/cupom/pacote existente
    @Override
    public Promocao update(Promocao promocao) {
        log.info("Atualizando ID {}: {}", promocao.getId(), promocao.getTitulo());
        validatePromocao(promocao); //Valida os dados
        return promocaoRepository.save(promocao); //Repassa para o repositório salvar no banco
    }

    // Exclui uma promoção/cupom/pacote
    @Override
    public void delete(Promocao promocao) {
        log.info("Removendo promoção ID {}: {}", promocao.getId(), promocao.getTitulo());

        String mensagem = String.format("Deseja realmente apagar %s - %s?", 
                                      promocao.getTipo(), 
                                      promocao.getTitulo());
        //Mostra diálogo de confirmação
        int confirmacao = JOptionPane.showConfirmDialog(
            null, 
            mensagem, 
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION); 

        //Se confirmado, remove do banco e mostra mensagem
        if (confirmacao == JOptionPane.YES_OPTION) {
            promocaoRepository.delete(promocao);
            JOptionPane.showMessageDialog(null, "Promoção excluída com sucesso!");
        }
    }

    // Retorna todas as promoções//cupons/pacotes cadastradas
    @Override
    public List<Promocao> findAll() {
        log.info("Listando todas as promoções");
        return promocaoRepository.findAll();
    }
    
    // Busca itens por ID
    @Override
    public Promocao findById(Long id) {
        log.info("Buscando promoção por ID: {}", id); 
        return promocaoRepository.findById(id).orElse(null); // Retorna null se não encontrar (orElse(null))
    }
    
    // Filtra promoções por tipo e termo de busca
    public List<Promocao> filtrarPromocoes(String termo, String tipo) {
        // Primeiro obtém as promoções por tipo
        List<Promocao> promocoesPorTipo = (List<Promocao>) tipoPromocaoRepository.findByNome(tipo);

        // Filtra pelo termo de busca
        String termoFiltrado = termo == null ? "" : termo;
        return promocaoRepository.filtrar((TipoPromocao) promocoesPorTipo, termoFiltrado);
    }
    // Validação geral da promoção
    
    // Verifica se não é nula e chama validadores específicos
    public void validatePromocao(Promocao promocao) {
        if (promocao == null) {
            throw new IllegalArgumentException("Preencha todos os campos");
        }
        
        validateTitulo(promocao.getTitulo()); 
        validateDescricao(promocao.getDescricao());
        validateDatas(promocao.getDataInicio(), promocao.getDataTermino());
        validateRegras(promocao.getRegras());
    }
    
    // Validações específicas do título
    private void validateTitulo(String titulo) {
        if (StringUtil.isNullOrEmpty(titulo)) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        if (!StringUtil.hasValidLength(titulo, 3, 20)) {
            throw new IllegalArgumentException("Título deve ter entre 3 e 20 caracteres");
        }
    }
    
    // Validações da descrição
    private void validateDescricao(String descricao) {
        if (StringUtil.isNullOrEmpty(descricao)) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }
        if (!StringUtil.hasValidLength(descricao, 10, 100)) {
            throw new IllegalArgumentException("Descrição deve ter entre 10 e 100 caracteres");
        }
    }
    
    // Validações das datas
    private void validateDatas(Date dataInicio, Date dataTermino) {
        if (dataInicio == null || dataTermino == null) {
            throw new IllegalArgumentException("Datas de início e término são obrigatórias");
        }
        
        if (dataTermino.before(dataInicio)) {
            throw new IllegalArgumentException("Data de término deve ser após a data de início");
        }
    }
    // Validações das regras
    private void validateRegras(String regras) {
        if (!StringUtil.isNullOrEmpty(regras) && !StringUtil.hasValidLength(regras, 0, 100)) {
            throw new IllegalArgumentException("Regras não podem exceder 100 caracteres");
        }
    }
}