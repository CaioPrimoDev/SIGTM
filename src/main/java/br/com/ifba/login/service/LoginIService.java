/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.login.service;

import br.com.ifba.usuario.comum.entity.Usuario;

/**
 *
 * @author User
 */
public interface LoginIService {
    
    Usuario autenticar(String email, String senha);
    
}
