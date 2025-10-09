package br.com.alura.comex.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Autenticacao;
import br.com.alura.comex.repository.AutenticacaoRepository;

@Service
public class AutenticacaoService {

    @Autowired
    private AutenticacaoRepository autenticacaoRepository;
    
    public void registraAutenticação(Autenticacao autenticacao) {
        autenticacaoRepository.save(autenticacao);
    }

    public boolean tokenÉVálido(String token) {
        var limiteInferior = Instant.now().minus(Duration.ofHours(1));
        var limiteSuperior = Instant.now();
        return autenticacaoRepository.existsByTokenAndInstanteBetween(token, limiteInferior, limiteSuperior);
    }

}
