/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pontoturistico.service;

import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.pontoturistico.repository.PontoTuristicoRepository;
import br.com.ifba.util.StringUtil;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author juant
 */
@Service
@RequiredArgsConstructor
public class PontoTuristicoService implements PontoTuristicoIService {

    // mensagem para tipos de erros distintos
    private static final String PONTO_TURISTICO_NULL = "O objeto Ponto Turístico não pode ser nulo.";
    private static final String PONTO_TURISTICO_NOT_FOUND = "Ponto Turístico com o ID informado não foi encontrado.";
    private static final String PONTO_TURISTICO_DUPLICADO = "Já existe um Ponto Turístico com o mesmo nome e localização.";

    private final PontoTuristicoRepository pontoTuristicoRepository;

    private static final Logger log = LoggerFactory.
                                        getLogger(PontoTuristicoService.class);

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
        if (StringUtil.isNullOrEmpty(pontoTuristico.getLocalizacao())) {
            throw new IllegalArgumentException("O campo 'localizacao' é obrigatório.");
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

    @Override
    public void save(PontoTuristico pontoTuristico) {
        log.info("Iniciando o salvamento do Ponto Turístico...");
        validarPontoTuristico(pontoTuristico);

        if (pontoTuristicoRepository.existsByNomeAndLocalizacao(pontoTuristico.getNome(), 
                                                                pontoTuristico.getLocalizacao())) {
            throw new IllegalStateException(PONTO_TURISTICO_DUPLICADO);
        }

        log.info("Ponto Turístico salvo com sucesso!");
        pontoTuristicoRepository.save(pontoTuristico);
    }

    @Override
    public void update(PontoTuristico pontoTuristico) {
        log.info("Iniciando a atualização do Ponto Turístico...");
        validarPontoTuristico(pontoTuristico);
        
        if (pontoTuristico.getId() == null || !pontoTuristicoRepository.existsById(pontoTuristico.getId())) {
            throw new NoSuchElementException(PONTO_TURISTICO_NOT_FOUND);
        }
        
        log.info("Ponto Turístico atualizado com sucesso!");
        pontoTuristicoRepository.save(pontoTuristico);
    }

    @Override
    public void delete(PontoTuristico pontoTuristico) {
        log.info("Iniciando a deleção do Ponto Turístico...");
        if (pontoTuristico == null || pontoTuristico.getId() == null) {
            throw new IllegalArgumentException(PONTO_TURISTICO_NULL);
        }

        PontoTuristico existente = this.findById(pontoTuristico.getId());
        
        pontoTuristicoRepository.delete(existente);
        log.info("Ponto Turístico deletado com sucesso!");
    }

    @Override
    public List<PontoTuristico> findAll() {
        log.info("Buscando todos os Pontos Turísticos.");
        return pontoTuristicoRepository.findAll();
    }

    @Override
    public PontoTuristico findById(Long id) {
        log.info("Buscando Ponto Turístico pelo ID: {}", id);
        if (id == null) {
            throw new IllegalArgumentException("O ID para busca não pode ser nulo.");
        }
        return pontoTuristicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(PONTO_TURISTICO_NOT_FOUND));
    }

    @Override
    public List<PontoTuristico> findByNomeStartingWithIgnoreCase(String nome) {
        log.info("Buscando Pontos Turísticos cujo nome começa com: {}", nome);
        
        return pontoTuristicoRepository.findByNomeStartingWithIgnoreCase(nome);
    }
}