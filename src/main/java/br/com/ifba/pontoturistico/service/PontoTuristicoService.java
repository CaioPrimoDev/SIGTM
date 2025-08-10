/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pontoturistico.service;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.endereco.service.EnderecoIService;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.pontoturistico.repository.PontoTuristicoRepository;
import br.com.ifba.util.StringUtil;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author juant
 */
@Service
@RequiredArgsConstructor
public class PontoTuristicoService implements PontoTuristicoIService {

    // Constantes de erro necessárias
    private static final String PONTO_TURISTICO_NULL = "Dados do Ponto Turístico não fornecidos.";
    private static final String PONTO_TURISTICO_NOT_FOUND = "Ponto Turístico com o ID informado não foi encontrado.";
    private static final String ENDERECO_JA_UTILIZADO = "Este endereço já está em uso por outro Ponto Turístico.";

    private static final Logger log = LoggerFactory.
                                        getLogger(PontoTuristicoService.class);

    private final PontoTuristicoRepository pontoTuristicoRepository;
    private final EnderecoIService enderecoService;

    /**
     * Valida os campos essenciais de um Ponto Turístico usando StringUtil.
     * @param pontoTuristico O objeto a ser validado.
     */
    private void validarPontoTuristico(PontoTuristico pontoTuristico) {
        if (pontoTuristico == null) {
            throw new IllegalArgumentException(PONTO_TURISTICO_NULL);
        }
        // Verifica se o campo foi preenchido devidamente
        if (StringUtil.isNullOrEmpty(pontoTuristico.getNome())) {
            throw new IllegalArgumentException("O campo 'nome' é obrigatório.");
        }
        // Verifica se o campo foi preenchido devidamente
        if (pontoTuristico.getEndereco() == null) {
            throw new IllegalArgumentException("Os dados de 'endereco' (endereço) são obrigatórios.");
        }
        // Verifica se o campo foi preenchido devidamente
        if (StringUtil.isNullOrEmpty(pontoTuristico.getHorarioAbertura())) {
            throw new IllegalArgumentException("O campo 'horarioAbertura' é obrigatório.");
        }
        // Valida o formato do horário de abertura
        if (!StringUtil.isValidHorario(pontoTuristico.getHorarioAbertura())) {
            throw new IllegalArgumentException("O formato do horário de abertura é inválido. Use HH:mm.");
        }
        // Verifica se o campo foi preenchido devidamente
        if (StringUtil.isNullOrEmpty(pontoTuristico.getHorarioFechamento())) {
            throw new IllegalArgumentException("O campo 'horarioFechamento' é obrigatório.");
        }
        // Valida o formato do horário de fechamento
        if (!StringUtil.isValidHorario(pontoTuristico.getHorarioFechamento())) {
            throw new IllegalArgumentException("O formato do horário de fechamento é inválido. Use HH:mm.");
        }
    }
    
    /**
     * Garante que um endereço seja usado por no máximo um Ponto Turístico.
     */
    private void validarUnicidadeDeEnderecoParaPontoTuristico(PontoTuristico pontoTuristico) {
        Optional<PontoTuristico> pontoExistente = pontoTuristicoRepository.findByEndereco(pontoTuristico.getEndereco());
        if (pontoExistente.isPresent() && !pontoExistente.get().getId().equals(pontoTuristico.getId())) {
            throw new IllegalStateException(ENDERECO_JA_UTILIZADO);
        }
    }

    @Override
    @Transactional
    public void save(PontoTuristico pontoTuristico) {       
        log.info("Iniciando processo de salvamento do Ponto Turístico: {}", pontoTuristico.getNome());
        
        validarPontoTuristico(pontoTuristico);
        
        Endereco enderecoGerenciado = enderecoService.encontrarOuCriarEndereco(pontoTuristico.getEndereco());
        pontoTuristico.setEndereco(enderecoGerenciado);
                
        validarUnicidadeDeEnderecoParaPontoTuristico(pontoTuristico);
        
        log.info("Validações concluídas. Salvando Ponto Turístico...");
        
        pontoTuristicoRepository.save(pontoTuristico);
    }
    
    @Override
    @Transactional
    public void update(PontoTuristico pontoTuristico) {
        log.info("Iniciando processo de atualização do Ponto Turístico de ID: {}", pontoTuristico.getId());

        if (pontoTuristico.getId() == null || !pontoTuristicoRepository.existsById(pontoTuristico.getId())) {
            throw new NoSuchElementException(PONTO_TURISTICO_NOT_FOUND);
        }

        this.save(pontoTuristico);
    }

    @Override
    @Transactional
    public void delete(PontoTuristico pontoTuristico) {
        if (pontoTuristico == null || pontoTuristico.getId() == null) {
            throw new IllegalArgumentException(PONTO_TURISTICO_NULL);
        }
        // Garante que o objeto existe antes de deletar
        PontoTuristico existente = this.findById(pontoTuristico.getId());
        pontoTuristicoRepository.delete(existente);
        log.info("Ponto Turístico deletado com sucesso!");
    }

    @Override
    @Transactional
    public List<PontoTuristico> findAll() {
        log.info("Buscando todos os Pontos Turísticos.");
        return pontoTuristicoRepository.findAll();
    }

    @Override
    @Transactional
    public PontoTuristico findById(Long id) {
        log.info("Buscando Ponto Turístico pelo ID: {}", id);
        if (id == null) {
            throw new IllegalArgumentException("O ID para busca não pode ser nulo.");
        }
        return pontoTuristicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(PONTO_TURISTICO_NOT_FOUND));
    }

    @Override
    @Transactional
    public List<PontoTuristico> findByNomeStartingWithIgnoreCase(String nome) {
        log.info("Buscando Pontos Turísticos cujo nome começa com: {}", nome);
        
        return pontoTuristicoRepository.findByNomeStartingWithIgnoreCase(nome);
    }
}