/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.reserva.controller;

import br.com.ifba.reserva.entity.Reserva;
import br.com.ifba.reserva.service.ReservaIService;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 *
 * @author juant
 */
@Controller
@RequiredArgsConstructor
public class ReservaController implements ReservaIController{
    
    private final ReservaIService reservaService;
    

    @Override
    public void save(Reserva reserva) {
        reservaService.save(reserva);
    }

    @Override
    public void delete(Reserva reserva) {
        reservaService.delete(reserva);
    }

    @Override
    public List<Reserva> findAll() {
        return reservaService.findAll();
    }

    @Override
    public Reserva findById(Long id) {
        return reservaService.findById(id);
    }
    
    @Override
    public List<Reserva> findByUsuario(Usuario usuario) {
        return reservaService.findByUsuario(usuario);
    }

    @Override
    public List<Reserva> findByTokenContainingIgnoreCase(String token) {
        return reservaService.findByTokenContainingIgnoreCase(token);
    }
}
