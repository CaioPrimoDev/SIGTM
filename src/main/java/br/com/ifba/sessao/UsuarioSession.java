/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.sessao;

import br.com.ifba.usuario.entity.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */

@Getter  @Setter
@Component
public class UsuarioSession {
    // Essa classe é um bean singleton, e pode injetá-la em qualquer outra tela ou classe.
    // Não é seguro para aplicações Web
    
    private Usuario usuarioLogado;
    
    public void limparSessao() {
        this.usuarioLogado = null;
    }

    public boolean isLogado() {
        return usuarioLogado != null;
    }
}
