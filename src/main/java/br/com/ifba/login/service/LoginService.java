/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.login.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> user = usuarioRepository.findByEmail(email);

        // Verifica se não é nulo (não existe usuario com esse e-mail)
        if (user.isPresent()) {
            Usuario usuario = user.get();

            // Verifica senha e status
            if (usuario.getSenha().equals(senha) && usuario.isStatus()) {
                return usuario;
            }
        }

        // Se não existir, ou senha incorreta, ou status = false
        return null;
    }
}

