/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.util;

/**
 *
 * @author User
 */
public class RegraNegocioException extends RuntimeException {
    
    // Ainda vamos E DEVEMOS usar Exceções especificos, como NullPointerException
    // Porém, podemos usar isso nas regras de negocio (camada service)
    // ficaria throw new RegraNegocioException("Mensagem personalizada");
    
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }

    public RegraNegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
