/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.reserva.controller;

import br.com.ifba.reserva.entity.Reserva;
import br.com.ifba.usuario.comum.entity.Usuario;
import java.util.List;

/**
 *
 * @author juant
 */
public interface ReservaIController {
    // metodos abstratos para ser implementado na classe
    public List<Reserva> findByUsuario(Usuario usuario);
    public void save(Reserva reserva);
    public void delete(Reserva reserva);
    public List<Reserva> findAll();
    public Reserva findById(Long id); 
    public List<Reserva> findByTokenContainingIgnoreCase(String token);
}
