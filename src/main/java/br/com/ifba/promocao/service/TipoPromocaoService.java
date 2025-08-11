package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.promocao.repository.TipoPromocaoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoPromocaoService implements TipoPromocaoIService {

    // Injeção do repositório que faz a comunicação com o banco
    @Autowired
    private TipoPromocaoRepository tipoPromocaoRepository;
    
    // Método para salvar com validações
    @Override
    public TipoPromocao save(TipoPromocao tipoPromocao) {
        // Valida se o título não está vazio
        if(tipoPromocao.getTitulo() == null || tipoPromocao.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título do tipo não pode ser vazio");
        }

        // Valida se a descrição não está vazia
        if(tipoPromocao.getDescricao() == null || tipoPromocao.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do tipo não pode ser vazia");
        }

        // Verifica se já existe um tipo com mesmo título
        if(tipoPromocaoRepository.existsByTitulo(tipoPromocao.getTitulo())) {
            throw new IllegalArgumentException("Já existe um tipo com este título");
        }

        // Se passou nas validações, salva no banco
        return tipoPromocaoRepository.save(tipoPromocao);
    }
    // Método para atualizar
    @Override
    public TipoPromocao update(TipoPromocao tipoPromocao) {
        // Verifica se o ID existe antes de atualizar
        if (!tipoPromocaoRepository.existsById(tipoPromocao.getId())) {
            throw new EntityNotFoundException("Tipo de promoção não encontrado");
        }
        return tipoPromocaoRepository.save(tipoPromocao);
    }

    // Método para deletar
    @Override
    public void delete(TipoPromocao tipoPromocao) {
        if(tipoPromocaoRepository.existsById(tipoPromocao.getId())) {
            tipoPromocaoRepository.delete(tipoPromocao);
        } else {
            throw new EntityNotFoundException("Tipo de promoção não encontrado");
        }
    }

    // Retorna todos os registros
    @Override
    public List<TipoPromocao> findAll() {
        return tipoPromocaoRepository.findAll();
    }

    // Busca por ID - se não encontrar, lança exceção
    @Override
    public TipoPromocao findById(Long id) {
        return tipoPromocaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo não encontrado"));
    }

    // Verifica se existe um tipo com determinado nome
    public boolean existsByNome(String nome) {
        return tipoPromocaoRepository.existsByTitulo(nome);
    }
}