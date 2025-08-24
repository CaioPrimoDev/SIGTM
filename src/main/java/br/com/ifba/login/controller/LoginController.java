/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.login.controller;

import br.com.ifba.login.service.LoginIService;
import br.com.ifba.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
@Controller
public class LoginController implements LoginIController {

    @Autowired
    private LoginIService loginService;

    @Override
    public Usuario login(String email, String senha) {
        return loginService.autenticar(email, senha);
    }
}

