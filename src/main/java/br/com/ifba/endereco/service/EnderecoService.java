/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.endereco.service;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.endereco.repository.EnderecoRepository;
import br.com.ifba.evento.repository.EventoRepository;
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
public class EnderecoService implements EnderecoIService {

    // Constantes para mensagens de erro
    private static final String ENDERECO_NULL = "Dados do Endereço não fornecidos.";
    private static final String ENDERECO_NOT_FOUND = "Endereço não encontrado na base de dados.";
    private static final String ENDERECO_DUPLICADO = "Já existe um endereço exatamente igual cadastrado.";
    private static final String ENDERECO_EM_USO = "Este endereço não pode ser deletado, pois está em uso por um Ponto Turístico ou Evento.";
    private static final String ID_NULL = "O ID do Endereço não pode ser nulo.";

    private static final Logger log = LoggerFactory.
                                        getLogger(EnderecoService.class);

    // Injeção dos repositórios
    private final EnderecoRepository enderecoRepository;
    private final PontoTuristicoRepository pontoTuristicoRepository;
    private final EventoRepository eventoRepository;

    /**
     * Valida os campos obrigatórios de um Endereço.
     */
    private void validarCamposObrigatorios(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException(ENDERECO_NULL);
        }
        if (StringUtil.isNullOrEmpty(endereco.getEstado())) {
            throw new IllegalArgumentException("O campo 'estado' é obrigatório.");
        }
        if (StringUtil.isNullOrEmpty(endereco.getCidade())) {
            throw new IllegalArgumentException("O campo 'cidade' é obrigatório.");
        }
        if (StringUtil.isNullOrEmpty(endereco.getBairro())) {
            throw new IllegalArgumentException("O campo 'bairro' é obrigatório.");
        }
    }

    /**
     * Valida se já existe um endereço idêntico no banco de dados.
     */
    private void validarDuplicidade(Endereco endereco) {
        Optional<Endereco> existente = enderecoRepository.findByEstadoAndCidadeAndBairroAndRuaAndNumero(
                endereco.getEstado(), endereco.getCidade(), endereco.getBairro(), endereco.getRua(), endereco.getNumero()
        );

        if (existente.isPresent() && !existente.get().getId().equals(endereco.getId())) {
            throw new IllegalStateException(ENDERECO_DUPLICADO);
        }
    }

    @Override
    @Transactional
    public Endereco save(Endereco endereco) {
        log.info("Iniciando salvamento do Endereço...");
        validarCamposObrigatorios(endereco);
        validarDuplicidade(endereco);
        log.info("Endereço validado. Salvando...");
        return enderecoRepository.save(endereco);
    }

    @Override
    @Transactional
    public Endereco update(Endereco endereco) {
        log.info("Iniciando atualização do Endereço de ID: {}", endereco.getId());
        if (endereco.getId() == null || !enderecoRepository.existsById(endereco.getId())) {
            throw new NoSuchElementException(ENDERECO_NOT_FOUND);
        }
        validarCamposObrigatorios(endereco);
        validarDuplicidade(endereco);
        log.info("Endereço validado. Atualizando...");
        return enderecoRepository.save(endereco);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Iniciando deleção do Endereço de ID: {}", id);
        if (id == null) {
            throw new IllegalArgumentException(ID_NULL);
        }
        if (!enderecoRepository.existsById(id)) {
            throw new NoSuchElementException(ENDERECO_NOT_FOUND);
        }

        enderecoRepository.deleteById(id);
        log.info("Endereço de ID {} deletado com sucesso.", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Endereco> findAll() {
        log.info("Buscando todos os Endereços.");
        return enderecoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Endereco findById(Long id) {
        log.info("Buscando Endereço pelo ID: {}", id);
        if (id == null) {
            throw new IllegalArgumentException(ID_NULL);
        }
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ENDERECO_NOT_FOUND));
    }
    
    @Override
    @Transactional
    public Endereco encontrarOuCriarEndereco(Endereco dadosEndereco) {
        // Valida se os campos básicos foram preenchidos
        validarCamposObrigatorios(dadosEndereco);

        // Usa o método para buscar um endereço idêntico
        Optional<Endereco> enderecoExistente = enderecoRepository.findByEstadoAndCidadeAndBairroAndRuaAndNumero(
                dadosEndereco.getEstado(),
                dadosEndereco.getCidade(),
                dadosEndereco.getBairro(),
                dadosEndereco.getRua(),
                dadosEndereco.getNumero()
        );

        // Se o endereço já existe, simplesmente o retorna.
        if (enderecoExistente.isPresent()) {
            log.info("Endereço já existente encontrado com ID: {}. Reutilizando.", enderecoExistente.get().getId());
            return enderecoExistente.get();
        }

        // Se não existe, salva o novo endereço e o retorna.
        log.info("Nenhum endereço correspondente encontrado. Criando um novo.");
        return enderecoRepository.save(dadosEndereco);
    }
}