/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.login.controller;

import br.com.ifba.usuario.comum.entity.Usuario;

/**
 *
 * @author User
 */
public interface LoginIController {
    Usuario login(String email, String senha);
}
