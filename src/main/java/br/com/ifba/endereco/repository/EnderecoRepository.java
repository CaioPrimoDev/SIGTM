/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.endereco.repository;

import br.com.ifba.endereco.entity.Endereco;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juant
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    // Busca um endereço pela combinação exata de seus campos
    // Usado para verificar se um endereço idêntico já existe na base de dados.
    Optional<Endereco> findByEstadoAndCidadeAndBairroAndRuaAndNumero(
                            String estado, String cidade, String bairro, String rua, String numero
                                );
}
