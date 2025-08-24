/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.parceiro.repository;

import br.com.ifba.parceiro.entity.Parceiro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface ParceiroRepository extends JpaRepository <Parceiro, Long> {
        Optional<Parceiro> findByCnpj(String cnpj);
            
        boolean existsByCnpj(String cnpj);
        
        List<Parceiro> findByNomeContainingIgnoreCase(String nome);
}
