/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.avaliacoes.repository;

import br.com.ifba.avaliacoes.entity.Avaliacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Todas as avaliações do ponto
    List<Avaliacao> findByPontoTuristicoIdOrderByEstrelasDesc(Long pontoId);

    // Melhores avaliações (estrelas >= param) do ponto, ordenadas desc
    List<Avaliacao> findByPontoTuristicoIdAndEstrelasGreaterThanEqualOrderByEstrelasDesc(Long pontoId, int estrelas);

    // Piores avaliações (estrelas <= param) do ponto, ordenadas asc
    List<Avaliacao> findByPontoTuristicoIdAndEstrelasLessThanEqualOrderByEstrelasAsc(Long pontoId, int estrelas);
}
