/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.reserva.entity;

import br.com.ifba.evento.entity.Evento;
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.pontoturistico.entity.PontoTuristico;
import br.com.ifba.usuario.comum.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author juant
 */
@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
/* 
"Esta funcionalidade deve permitir ao usuário reservar horários 
em atrações turísticas. As ações disponíveis serão reservar, 
desmarcar, pesquisar, listar reservas, somente usuários logados 
podem fazer isso. Cada reserva possui um token único para 
identificação, ponto turistico, horário e usuário que a marcou"
*/
public class Reserva extends PersistenceEntity{

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "data_reserva", nullable = false)
    private LocalDate dataReserva;

    /**
     * Relacionamento Muitos-para-Um com Usuario.
     * Muitas reservas podem pertencer a um único usuário.
     * A anotação @JoinColumn cria a chave estrangeira 'usuario_id' na tabela 'reservas'.
     */
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /**
     * Relacionamento com PontoTuristico.
     * Pode ser nulo se a reserva for para um Evento.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ponto_turistico_id", nullable = true) // Alterado para nullable = true
    private PontoTuristico pontoTuristico;

    /**
     * Relacionamento com Evento.
     * Pode ser nulo se a reserva for para um Ponto Turístico.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "evento_id", nullable = true) // Novo relacionamento
    private Evento evento;
    
    /**
     * Validação de negócio para garantir que ou o ponto turístico ou o evento
     * seja preenchido, mas não ambos. Isso é um XOR (ou exclusivo).
     * A anotação @AssertTrue garante que este método retorne 'true' durante a validação.
     */
    @AssertTrue(message = "A reserva deve ser para um Ponto Turístico OU um Evento, mas não ambos.")
    private boolean isItemValido() {
        // Retorna true se um for nulo e o outro não.
        return (pontoTuristico != null && evento == null) || (pontoTuristico == null && evento != null);
    }
    
    /**
     * Método utilitário (não mapeado no banco) para facilitar
     * o acesso ao nome do item reservado, seja ele qual for.
     */
    @Transient // Indica que este método não deve ser persistido
    public String getNomeItemReservado() {
        if (pontoTuristico != null) {
            return pontoTuristico.getNome();
        }
        if (evento != null) {
            return evento.getNome();
        }
        return "Item não informado";
    }

    @Override
    public String toString() {
        // formata a data para melhor entendimento
        java.time.format.DateTimeFormatter formatter = 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return String.format(
            "DETALHES DA RESERVA:\n\n" +
            "  - Token: %s\n" +
            "  - Data: %s\n" +
            "  - Usuário: %s\n" +
            "  - Item Reservado: %s", // <-- Linha única e genérica
            this.token,
            (this.dataReserva != null ? this.dataReserva.format(formatter) : "Não informada"),
            (this.usuario != null ? this.usuario.getNome() : "Não informado"),
            this.getNomeItemReservado()
        );
    }
}