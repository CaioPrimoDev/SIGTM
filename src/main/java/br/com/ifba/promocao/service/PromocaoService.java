package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.Promocao;
import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.promocao.repository.PromocaoRepository;
import br.com.ifba.promocao.repository.TipoPromocaoRepository;
import br.com.ifba.sessao.UsuarioSession;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.util.StringUtil;
import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocaoService implements PromocaoIService {
    
    @Autowired
    private PromocaoRepository promocaoRepository;
    
    @Autowired
    private TipoPromocaoRepository tipoPromocaoRepository;
    
    @Autowired
    private UsuarioSession usuarioSession;
     
    private static final Logger log = LoggerFactory.getLogger(PromocaoService.class);
       
    @Override
    public Promocao save(Promocao promocao) {
        log.info("Salvando {}", promocao.getTitulo());

        // obtem o usuário logado
        Usuario usuarioLogado = usuarioSession.getUsuarioLogado();
        if (usuarioLogado == null) {
            throw new RuntimeException("Usuário não autenticado. Não é possível salvar a promoção.");
        }

        //associa promoção a usuário
        promocao.setUsuarioCriador(usuarioLogado);

        //valida e salve a entidade
        validatePromocao(promocao);
        return promocaoRepository.save(promocao);
    }
    @Override
    public Promocao update(Promocao promocao) {
        log.info("Atualizando ID {}: {}", promocao.getId(), promocao.getTitulo());

        //Obtem a promoção existente do banco de dados
        Promocao promocaoExistente = findById(promocao.getId());

        //Define o usuário criador da nova promoção para o usuário criador da promoção existente
        promocao.setUsuarioCriador(promocaoExistente.getUsuarioCriador());

        // valida e salve
        validatePromocao(promocao);
        return promocaoRepository.save(promocao);
    }
    @Override
    public void delete(Promocao promocao) {
        log.info("Removendo promoção ID {}: {}", promocao.getId(), promocao.getTitulo());
        promocaoRepository.delete(promocao);
    }

    @Override
    public List<Promocao> findAll() {
        log.info("Listando todas as promoções");
        return promocaoRepository.findAll();
    }
    
    @Override
    public Promocao findById(Long id) {
        log.info("Buscando promoção por ID: {}", id); 
        return promocaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Promoção não encontrada"));
    }

    public List<Promocao> filtrarPromocoes(String termo, String tipo) {
        if(tipo.equals("TODOS")) {
            if(termo == null || termo.isEmpty()) {
                return promocaoRepository.findAll();
            }
            return promocaoRepository.findByTituloContainingIgnoreCase(termo);
        } else {
            // Primeiro verifica se existe algum tipo com esse nome
            List<TipoPromocao> tipos = (List<TipoPromocao>) tipoPromocaoRepository.findByTitulo(tipo);

            if(tipos == null || tipos.isEmpty()) {
                throw new EntityNotFoundException("Tipo não encontrado");
            }

            // Pega o primeiro tipo encontrado (assumindo que nomes são únicos)
            TipoPromocao tipoPromocao = tipos.get(0);

            if(termo == null || termo.isEmpty()) {
                return promocaoRepository.findByTipo(tipoPromocao);
            }
            return promocaoRepository.findByTipoAndTituloContainingIgnoreCase(tipoPromocao, termo);
        }
    }
    
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