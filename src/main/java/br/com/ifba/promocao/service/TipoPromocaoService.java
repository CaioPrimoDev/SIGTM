package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.TipoPromocao;
import br.com.ifba.promocao.repository.TipoPromocaoRepository;
import br.com.ifba.sessao.UsuarioSession;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioService;
import br.com.ifba.util.RegraNegocioException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoPromocaoService implements TipoPromocaoIService {

    // Injeção do repositório que faz a comunicação com o banco
    @Autowired
    private TipoPromocaoRepository tipoPromocaoRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioSession usuarioSession;
     // Método auxiliar para validar permissões de cadastro
    private void validarPermissaoCadastro() {
        Usuario usuarioLogado = usuarioSession.getUsuarioLogado();
        
        if (usuarioLogado == null) {
            throw new RegraNegocioException("Usuário não autenticado");
        }

        // Busca o usuário completo para verificar o tipo
        Usuario usuarioCompleto = usuarioService.findById(usuarioLogado.getPessoa().getId());
        
        // Verifica se é parceiro ou gestor
        String tipoUsuario = usuarioCompleto.getTipo().getNome().toLowerCase();
        if (!tipoUsuario.equals("parceiro") && !tipoUsuario.equals("gestor")) {
            throw new RegraNegocioException("Apenas parceiros e gestores podem cadastrar tipos de promoção");
        }
    }

    // Método auxiliar para validar visualização
    private void validarPermissaoVisualizacao() {
        Usuario usuarioLogado = usuarioSession.getUsuarioLogado();
        
        if (usuarioLogado == null) {
            throw new RegraNegocioException("Usuário não autenticado para visualização");
        }
    }

    // Método auxiliar para obter usuário logado
    private Usuario getUsuarioLogado() {
        Usuario usuarioLogado = usuarioSession.getUsuarioLogado();
        if (usuarioLogado == null) {
            throw new RegraNegocioException("Usuário não autenticado");
        }
        return usuarioLogado;
    }
    
    // Método para salvar com validações
    @Override
    public TipoPromocao save(TipoPromocao tipoPromocao) {
        // Valida permissão do usuário
        validarPermissaoCadastro();

        // Validações existentes
        if(tipoPromocao.getTitulo() == null || tipoPromocao.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título do tipo não pode ser vazio");
        }

        if(tipoPromocao.getDescricao() == null || tipoPromocao.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do tipo não pode ser vazia");
        }

        if(tipoPromocaoRepository.existsByTitulo(tipoPromocao.getTitulo())) {
            throw new IllegalArgumentException("Já existe um tipo com este título");
        }

        // Associa o usuário que está cadastrando
        tipoPromocao.setUsuarioCadastro(getUsuarioLogado());

        return tipoPromocaoRepository.save(tipoPromocao);
    }
    
    // Método para atualizar
    @Override
    public TipoPromocao update(TipoPromocao tipoPromocao) {
        validarPermissaoCadastro();

        // Verifica se o registro existe
        if (!tipoPromocaoRepository.existsById(tipoPromocao.getId())) {
            throw new EntityNotFoundException("Tipo de promoção não encontrado");
        }

        // Busca o registro existente
        TipoPromocao existente = findById(tipoPromocao.getId());
        
        // Obtém usuário logado
        Usuario usuarioLogado = getUsuarioLogado();
        
        // Verifica se o usuário é o dono do registro ou é gestor
        String tipoUsuario = usuarioLogado.getTipo().getNome().toLowerCase();
        boolean isGestor = tipoUsuario.equals("gestor");
        boolean isDono = existente.getUsuarioCadastro().getPessoa().getId().equals(usuarioLogado.getPessoa().getId());
        
        if (!isDono && !isGestor) {
            throw new RegraNegocioException("Apenas o criador ou gestores podem editar este tipo de promoção");
        }

        return tipoPromocaoRepository.save(tipoPromocao);
    }

    // Método para deletar
    @Override
    public void delete(Long id) {
        validarPermissaoCadastro();

        // Busca o registro
        TipoPromocao tipoPromocao = findById(id);
        
        // Obtém usuário logado
        Usuario usuarioLogado = getUsuarioLogado();
        
        // Verifica se o usuário é o dono do registro ou é gestor
        String tipoUsuario = usuarioLogado.getTipo().getNome().toLowerCase();
        boolean isGestor = tipoUsuario.equals("gestor");
        boolean isDono = tipoPromocao.getUsuarioCadastro().getPessoa().getId().equals(usuarioLogado.getPessoa().getId());
        
        if (!isDono && !isGestor) {
            throw new RegraNegocioException("Apenas o criador ou gestores podem excluir este tipo de promoção");
        }

        tipoPromocaoRepository.delete(tipoPromocao);
    }

    // Retorna todos os registros
    @Override
    public List<TipoPromocao> findAll() {
        return tipoPromocaoRepository.findAll();
    }

    // Busca por ID - se não encontrar, lança exceção
    @Override
    public TipoPromocao findById(Long id) {
        validarPermissaoVisualizacao();
        return tipoPromocaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo não encontrado"));
    }

    // Verifica se existe um tipo com determinado nome
    public boolean existsByNome(String nome) {
        return tipoPromocaoRepository.existsByTitulo(nome);
    }

}    
