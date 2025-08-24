/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.promocao.service;

import br.com.ifba.promocao.entity.PublicoPromocao;
import br.com.ifba.promocao.repository.PublicoPromocaoRepository;
import br.com.ifba.sessao.UsuarioSession;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioService;
import br.com.ifba.util.RegraNegocioException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joice
 */
@Service
public class PublicoPromocaoService implements PublicoPromocaoIService {
    

    @Autowired
    private PublicoPromocaoRepository publicoPromocaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioSession usuarioSession;

    private Usuario getUsuarioLogado() {
        Usuario usuarioLogado = usuarioSession.getUsuarioLogado();
        if (usuarioLogado == null) {
            throw new RegraNegocioException("Usuário não autenticado");
        }
        return usuarioLogado;
    }

    private void validarPermissaoCadastro() {
        Usuario usuario = getUsuarioLogado();
        String tipoUsuario = usuarioService.findById(usuario.getPessoa().getId())
                                         .getTipo().getNome().toLowerCase();
        if (!tipoUsuario.equals("parceiro") && !tipoUsuario.equals("gestor")) {
            throw new RegraNegocioException("Apenas parceiros e gestores podem cadastrar públicos");
        }
    }

    private void validarPermissaoVisualizacao() {
        getUsuarioLogado(); // apenas garante que o usuário está logado
    }

    @Override
    public PublicoPromocao save(PublicoPromocao publicoPromocao) {
        validarPermissaoCadastro();

        if (publicoPromocao.getNome() == null || publicoPromocao.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if (publicoPromocao.getDescricao() == null || publicoPromocao.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }

        if (publicoPromocao.getFaixaEtaria() == null || publicoPromocao.getFaixaEtaria().trim().isEmpty()) {
            throw new IllegalArgumentException("Faixa etária não pode ser vazia");
        }

        if (publicoPromocao.getInteresse() == null || publicoPromocao.getInteresse().trim().isEmpty()) {
            throw new IllegalArgumentException("Interesse não pode ser vazio");
        }

        publicoPromocao.setUsuarioCadastro(getUsuarioLogado());
        return publicoPromocaoRepository.save(publicoPromocao);
    }

    @Override
    public PublicoPromocao update(PublicoPromocao publicoPromocao) {
        validarPermissaoCadastro();

        if (!publicoPromocaoRepository.existsById(publicoPromocao.getId())) {
            throw new EntityNotFoundException("Público não encontrado");
        }

        PublicoPromocao existente = findById(publicoPromocao.getId());
        Usuario usuarioLogado = getUsuarioLogado();
        String tipoUsuario = usuarioLogado.getTipo().getNome().toLowerCase();
        boolean isGestor = tipoUsuario.equals("gestor");
        boolean isDono = existente.getUsuarioCadastro().getPessoa().getId().equals(usuarioLogado.getPessoa().getId());

        if (!isDono && !isGestor) {
            throw new RegraNegocioException("Apenas o criador ou gestores podem editar este público");
        }

        publicoPromocao.setUsuarioCadastro(existente.getUsuarioCadastro()); // mantém o dono
        return publicoPromocaoRepository.save(publicoPromocao);
    }

    @Override
    public void delete(Long id) {
        validarPermissaoCadastro();

        PublicoPromocao publico = findById(id);
        Usuario usuarioLogado = getUsuarioLogado();
        String tipoUsuario = usuarioLogado.getTipo().getNome().toLowerCase();
        boolean isGestor = tipoUsuario.equals("gestor");
        boolean isDono = publico.getUsuarioCadastro().getPessoa().getId().equals(usuarioLogado.getPessoa().getId());

        if (!isDono && !isGestor) {
            throw new RegraNegocioException("Apenas o criador ou gestores podem excluir este público");
        }

        publicoPromocaoRepository.delete(publico);
    }

    @Override
    public List<PublicoPromocao> findAll() {
        return publicoPromocaoRepository.findAll();
    }

    @Override
    public PublicoPromocao findById(Long id) {
        return publicoPromocaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Público não encontrado"));
    }
}
