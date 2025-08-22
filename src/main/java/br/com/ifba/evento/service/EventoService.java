/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.evento.service;

import br.com.ifba.evento.entity.Evento;
import br.com.ifba.evento.repository.EventoRepository;
import br.com.ifba.util.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import br.com.ifba.util.StringUtil;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author Casa
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public boolean save(Evento evento) {
        validarEvento(evento);
        try {
            eventoRepository.save(evento);
            log.info("Evento salvo com sucesso: {}", evento.getNome());
            return true;
        } catch (DataIntegrityViolationException e) {
            log.error("Violação de integridade ao salvar Evento: {}. Erro: {}", evento.getNome(), e.getMessage());
            throw new RegraNegocioException("Já existe um evento com esse nome ou dados inválidos.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao salvar Evento: {}. Stacktrace: {}", evento.getNome(), e);
            throw new RegraNegocioException("Erro ao salvar Evento.");
        }
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            log.warn("Tentativa de excluir Evento com ID inválido: {}", id);
            throw new RegraNegocioException("ID de Evento inválido.");
        }

        try {
            eventoRepository.deleteById(id);
            log.info("Evento excluído com sucesso. ID: {}", id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Tentativa de exclusão de Evento inexistente. ID: {}. Erro: {}", id, e.getMessage());
            throw new RegraNegocioException("Evento não encontrado para exclusão.");
        } catch (DataIntegrityViolationException e) {
            log.error("Violação de integridade ao excluir Evento. ID: {}. Erro: {}", id, e.getMessage());
            throw new RegraNegocioException("Evento não pode ser excluído pois está vinculado a outros registros.");
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao excluir Evento. ID: {}. Stacktrace: {}", id, e);
            throw new RegraNegocioException("Erro ao excluir Evento.");
        }
    }

    public List<Evento> findAll() {
        try {
            List<Evento> eventos = eventoRepository.findAll();
            log.info("Busca de todos os eventos retornou {} registros", eventos.size());
            return eventos;
        } catch (RuntimeException e) {
            log.error("Erro ao buscar todos os Eventos. Stacktrace: {}", e);
            throw new RegraNegocioException("Erro ao buscar eventos.");
        }
    }

    public Evento findById(Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido fornecido para busca de Evento: {}", id);
            throw new RegraNegocioException("ID inválido para busca.");
        }

        try {
            return eventoRepository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("Evento não encontrado para ID: {}", id);
                        return new RegraNegocioException("Evento não encontrado.");
                    });
        } catch (RuntimeException e) {
            log.error("Erro inesperado ao buscar Evento por ID: {}. Stacktrace: {}", id, e);
            throw new RegraNegocioException("Erro ao buscar evento.");
        }
    }
    
// METODOS JPA REPOSITORY ESPECIFICOS   
  
  public List<Evento> findByCategoriaContainingIgnoreCase(String categoria) {
    try {
        // Validação do parâmetro de entrada
        if (!StringUtil.isNullOrEmpty(categoria)) {
            log.warn("Tentativa de busca de eventos com categoria vazia ou nula");
            throw new RegraNegocioException("Categoria não pode ser vazia.");
        }
        
        // Limitar o tamanho da categoria para evitar buscas maliciosas
        if (categoria.length() > 100) {
            log.warn("Tentativa de busca com categoria muito longa: {}", categoria);
            throw new RegraNegocioException("Categoria muito longa para pesquisa.");
        }
        
        log.info("Buscando eventos pela categoria: {}", categoria);
        List<Evento> eventos = eventoRepository.findByCategoriaContainingIgnoreCase(categoria);
        
        // Verificar se encontrou resultados
        if (eventos.isEmpty()) {
            log.info("Nenhum evento encontrado para a categoria: {}", categoria);
        } else {
            log.info("Encontrados {} eventos para a categoria: {}", eventos.size(), categoria);
        }
        
        return eventos;
        
    } catch (RegraNegocioException e) {
        // Exceções de negócio já tratadas e logadas
        throw e;
        
    } catch (RuntimeException e) {
        log.error("Erro inesperado ao buscar eventos pela categoria: {}. Erro: {}", categoria, e.getMessage(), e);
        throw new RegraNegocioException("Erro ao buscar eventos por categoria.");
    }
  }
  
  public List<Evento> findByNomeContainingIgnoreCase(String eventoNome) {
    try {
        // Validação básica do parâmetro
        if (StringUtil.isNullOrEmpty(eventoNome)) {
            log.warn("Tentativa de busca de eventos com nome vazio ou nulo");
            throw new RegraNegocioException("Nome do evento não pode ser vazio.");
        }
        
        // Sanitização da entrada
        String nomeSanitizado = eventoNome.trim();
        
        // Validação de segurança contra buscas maliciosas
        if (nomeSanitizado.length() < 3) {
            log.warn("Tentativa de busca com termo muito curto: '{}'", nomeSanitizado);
            throw new RegraNegocioException("Termo de busca deve ter pelo menos 3 caracteres.");
        }
        
        if (nomeSanitizado.length() > 100) {
            log.warn("Tentativa de busca com termo muito longo: {} caracteres", nomeSanitizado.length());
            throw new RegraNegocioException("Termo de busca muito extenso.");
        }
        
        log.info("Buscando eventos por nome: '{}'", nomeSanitizado);
        List<Evento> eventos = eventoRepository.findByNomeContainingIgnoreCase(nomeSanitizado);
        
        // Verificação de resultados
        if (eventos.isEmpty()) {
            log.info("Nenhum evento encontrado para: '{}'", nomeSanitizado);
        } else {
            log.info("Encontrados {} eventos para: '{}'", eventos.size(), nomeSanitizado);
        }
        
        return eventos;
        
    } catch (RegraNegocioException e) {
        // Exceções de negócio já tratadas
        throw e;
        
    } catch (RuntimeException e) {
        log.error("Erro ao buscar eventos por nome: '{}'. Erro: {}", eventoNome, e.getMessage(), e);
        throw new RegraNegocioException("Erro na busca de eventos.");
    }
}

    StringUtil strUtil;

    // VALIDAÇAO DE EVENTO
    
    public void validarEvento(Evento evento) {

        if (strUtil.isNullOrEmpty(evento.getNome())) {
            log.info("Nome é obrigatório");
            throw new RegraNegocioException("O nome não pode ser nulo ou vazio");
        }

        if (strUtil.isNullOrEmpty(evento.getEndereco().getBairro())) {
            log.info("Bairro é obrigatório");
            throw new RegraNegocioException("O bairro não pode ser nulo ou vazio");

        }
        if (strUtil.isNullOrEmpty(evento.getEndereco().getCidade())) {
            log.info("Cidade é obrigatória");
            throw new RegraNegocioException("A Cidade não pode ser nula ou vazia");

        }
        if (strUtil.isNullOrEmpty(evento.getEndereco().getEstado())) {
            log.info("Estado é obrigatório");
            throw new RegraNegocioException("Estado não pode ser nulo ou vazio");

        }
        if (strUtil.isNullOrEmpty(evento.getEndereco().getNumero())) {
            log.info("Número da rua é obrigatório");
            throw new RegraNegocioException("Número da rua não pode ser nulo ou vazio");

        }
        
        if (strUtil.isNullOrEmpty(evento.getEndereco().getRua())) {
            log.info("Rua é obrigatória");
            throw new RegraNegocioException("A rua não pode ser nula ou vazia");

        }

        if (!StringUtil.isNullOrEmpty(evento.getCategoria())) {
        } else {
            log.info("Categoria é obrigatória");
            throw new RegraNegocioException("A categoria não pode ser nula ou vazia");
        }

        if (strUtil.isNullOrEmpty(evento.getDescricao())) {
            log.info("Descrição é obrigatória");
            throw new RegraNegocioException("A descrição não pode ser nula ou vazia");
        }

        if (strUtil.isNullOrEmpty(evento.getProgramacao())) {
            log.info("Programação é obrigatória");
            throw new RegraNegocioException("A programação alvo não pode ser nula ou vazia");
        }

        if (strUtil.isNullOrEmpty(evento.getPublicoAlvo())) {
            log.info("Publico alvo é obrigatório");
            throw new RegraNegocioException("O público alvo não pode ser nulo ou vazio");
        }

        if (evento.getNivelAcessibilidade() > 10 || evento.getNivelAcessibilidade() < 1) {
            log.info("Nível de acessibilidade é um valor inteiro entre 1 e 10");
            throw new RegraNegocioException("Nível de acessibilidade não pode fugir do intervalo de 1 ate 10");

        }

    }

}
