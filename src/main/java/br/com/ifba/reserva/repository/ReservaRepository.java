/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.reserva.repository;

import br.com.ifba.evento.entity.Evento;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.reserva.entity.Reserva;
import br.com.ifba.usuario.comum.entity.Usuario;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juant
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    // método para buscar por usuário
    List<Reserva> findByUsuario(Usuario usuario);
    
    // Método para validar conflito com Ponto Turístico
    List<Reserva> findByUsuarioAndPontoTuristicoAndDataReserva(Usuario usuario, PontoTuristico pontoTuristico, LocalDate dataReserva);
    
    // Método para validar conflito com Evento
    List<Reserva> findByUsuarioAndEventoAndDataReserva(Usuario usuario, Evento evento, LocalDate dataReserva);
    
    // Busca uma reserva pelo seu token. Usado pelo gestor.
    List<Reserva> findByTokenContainingIgnoreCase(String token);

    // Busca uma reserva pelo token, mas apenas se pertencer ao usuário especificado.
    List<Reserva> findByTokenContainingIgnoreCaseAndUsuario(String token, Usuario usuario);

}
