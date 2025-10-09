package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.model.Autenticacao;

import java.time.Instant;

@Repository
public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Long> {
    
    boolean existsByTokenAndInstanteBetween(String token, Instant limiteInferior, Instant limiteSuperior);

}
