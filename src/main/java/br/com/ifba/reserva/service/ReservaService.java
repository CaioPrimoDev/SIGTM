/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.reserva.service;

import br.com.ifba.evento.repository.EventoRepository;
import br.com.ifba.pontoturistico.repository.PontoTuristicoRepository;
import br.com.ifba.reserva.entity.Reserva;
import br.com.ifba.reserva.repository.ReservaRepository;
import br.com.ifba.sessao.UsuarioSession;
import br.com.ifba.usuario.comum.entity.Usuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author juant
 */
@Service
@RequiredArgsConstructor
public class ReservaService implements ReservaIService {

    // Constantes de erro e mensagens
    private static final String RESERVA_NULL = "Dados da Reserva não fornecidos.";
    private static final String RESERVA_NOT_FOUND = "Reserva com o ID informado não foi encontrada.";
    private static final String PERMISSAO_NEGADA = "Permissão negada. A reserva não pertence ao usuário logado ou o usuário não é um gestor.";
    private static final String USUARIO_NAO_AUTENTICADO = "Acesso negado. Nenhum usuário autenticado na sessão.";
    private static final String GESTOR = "GESTOR";
    private static final String ITEM_RESERVA_OBRIGATORIO = "A reserva deve estar associada a um Ponto Turístico ou a um Evento.";
    private static final String ITEM_INEXISTENTE = "O Ponto Turístico ou Evento selecionado para a reserva não existe.";
    private static final String USUARIO_JA_POSSUI_RESERVA = "O usuário já possui uma reserva para este item nesta data.";

    private static final Logger log = LoggerFactory.getLogger(ReservaService.class);

    private final ReservaRepository reservaRepository;
    private final PontoTuristicoRepository pontoTuristicoRepository; // Dependência para validar Ponto Turístico
    private final UsuarioSession usuarioLogado;
    private final EventoRepository eventoRepository; // // Dependência para validar Evento
    
    
    // Valida os campos essenciais de uma Reserva.
    private void validarDadosReserva(Reserva reserva) {
        if (reserva == null) throw new IllegalArgumentException(RESERVA_NULL);
        if (reserva.getUsuario() == null) throw new IllegalArgumentException("O usuário da reserva é obrigatório.");

        // Valida se pelo menos um e apenas um item foi associado
        if (reserva.getPontoTuristico() == null && reserva.getEvento() == null) {
            throw new IllegalArgumentException(ITEM_RESERVA_OBRIGATORIO);
        }
        if (reserva.getPontoTuristico() != null && reserva.getEvento() != null) {
            throw new IllegalArgumentException("A reserva não pode ser para um Ponto Turístico e um Evento simultaneamente.");
        }
        
        // Valida a existência do item que foi preenchido
        if (reserva.getPontoTuristico() != null) {
            if (!pontoTuristicoRepository.existsById(reserva.getPontoTuristico().getId())) {
                throw new IllegalArgumentException(ITEM_INEXISTENTE);
            }
        } else { // Se não é ponto turístico, é evento
            if (!eventoRepository.existsById(reserva.getEvento().getId())) {
                throw new IllegalArgumentException(ITEM_INEXISTENTE);
            }
        }

        // Valida a data
        if (reserva.getDataReserva() == null || reserva.getDataReserva().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da reserva deve ser futura.");
        }
    }

    // Verifica se já existe uma reserva para o mesmo usuário, no mesmo ponto turístico e na mesma data.
    private void validarConflitoDeReserva(Reserva reserva) {
        List<Reserva> reservasExistentes;
        
        
        if (reserva.getPontoTuristico() != null) {
            reservasExistentes = reservaRepository.findByUsuarioAndPontoTuristicoAndDataReserva(
                reserva.getUsuario(),
                reserva.getPontoTuristico(),
                reserva.getDataReserva()
            );
        } 
        else {
            reservasExistentes = reservaRepository.findByUsuarioAndEventoAndDataReserva(
                reserva.getUsuario(),
                reserva.getEvento(),
                reserva.getDataReserva()
            );
        }

        if (!reservasExistentes.isEmpty() && !reservasExistentes.get(0).getId().equals(reserva.getId())) {
            throw new IllegalStateException(USUARIO_JA_POSSUI_RESERVA);
        }
    }
    
    // Valida se o usuário logado tem permissão para acessar ou modificar a reserva.
    // Permissão é concedida se o usuário for o dono da reserva ou se for um GESTOR.
    private void validarPermissao(Reserva reserva) {
        Usuario usuario = usuarioLogado.getUsuarioLogado();
        
        if (usuario == null) {
            throw new IllegalStateException(USUARIO_NAO_AUTENTICADO);
        }
        
        // Verifica se o usuário logado é GESTOR
        boolean isGestor = GESTOR.equals(usuario.getTipo().getNome());
        
        // Verifica se o ID do usuário logado é o mesmo do dono da reserva
        boolean isDonoDaReserva = reserva.getUsuario().getId().equals(usuario.getId());
        
        // Se não for gestor E não for o dono da reserva, nega a permissão
        if (!isGestor && !isDonoDaReserva) {
            throw new IllegalStateException(PERMISSAO_NEGADA);
        }
    }


    @Override
    @Transactional
    public void save(Reserva reserva) {
        log.info("Iniciando processo de salvamento de Reserva...");

        // Garante que o usuário da reserva é o que está logado na sessão
        Usuario usuarioSessao = usuarioLogado.getUsuarioLogado();
        if (usuarioSessao == null) {
            throw new IllegalStateException(USUARIO_NAO_AUTENTICADO);
        }
        reserva.setUsuario(usuarioSessao);
        
        // Gera um UUID completo, que é garantidamente único
        String uuid = UUID.randomUUID().toString();

        // Pega apenas os 8 primeiros caracteres do UUID para a parte única
        String parteUnica = uuid.substring(0, 8);

        // Junta tudo para formar o token final
        String tokenFinal = "SIGTM-" + parteUnica;
        
        // Atribui o token à reserva
        reserva.setToken(tokenFinal);

        // Executa as validações de negócio
        validarDadosReserva(reserva);
        validarConflitoDeReserva(reserva);

        log.info("Validações concluídas. Salvando Reserva...");
        reservaRepository.save(reserva);
    }
    
    @Override
    @Transactional
    public void delete(Reserva reserva){
        if (reserva == null || reserva.getId() == null) {
            throw new IllegalArgumentException(RESERVA_NULL);
        }
        
        
        // Garante que o objeto existe antes de deletar
        Reserva existente = this.findById(reserva.getId());
        reservaRepository.delete(existente);
        log.info("Reserva desmarcada com sucesso!");
    }
    
    @Override
    public Reserva findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID para busca não pode ser nulo.");
        }
        
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(RESERVA_NOT_FOUND));
        
        // Verifica se o usuário tem permissão para visualizar a reserva
        validarPermissao(reserva);
        
        return reserva;
    }

    @Override
    public List<Reserva> findAll(){
        Usuario usuario = usuarioLogado.getUsuarioLogado();
        if (usuario == null) {
            throw new IllegalStateException(USUARIO_NAO_AUTENTICADO);
        }
        
        // Se for GESTOR, retorna todas as reservas
        if (GESTOR.equals(usuario.getTipo().getNome())) {
            log.info("Usuário GESTOR buscando todas as reservas.");
            return reservaRepository.findAll();
        }
        
        // Se for um usuário comum, retorna apenas as suas próprias reservas
        log.info("Buscando todas as reservas para o usuário de ID: {}", usuario.getId());
        return reservaRepository.findByUsuario(usuario);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Reserva> findByUsuario(Usuario usuario) { 
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        log.info("Buscando todas as reservas para o usuário de ID: {}", usuario.getId());
        return reservaRepository.findByUsuario(usuario);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Reserva> findByTokenContainingIgnoreCase(String token) {
        // Pega o usuário logado na sessão
        Usuario usuario = usuarioLogado.getUsuarioLogado();
        if (usuario == null) {
            throw new IllegalStateException("Acesso negado. Nenhum usuário autenticado na sessão.");
        }
        
        // Verifica se o usuário é um gestor
        boolean isGestor = "GESTOR".equals(usuario.getTipo().getNome());
        
        if (isGestor) {
            // Se for gestor, busca qualquer reserva pelo token
            log.info("Gestor buscando reserva pelo token: {}", token);
            return reservaRepository.findByTokenContainingIgnoreCase(token);
        } else {
            // Se for usuário comum, busca a reserva pelo token APENAS se pertencer a ele
            log.info("Usuário {} buscando própria reserva pelo token: {}", usuario.getId(), token);
            return reservaRepository.findByTokenContainingIgnoreCaseAndUsuario(token, usuario);
        }
    }
}