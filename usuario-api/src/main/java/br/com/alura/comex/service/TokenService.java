package br.com.alura.comex.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuárioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String ISSUER = "API comex"; 

    @Value("${api.comex.token.secret}")
    private String tokenSecret;

    @Autowired
    UsuárioRepository usuaRepository;

    public String geraToken(Usuario usuário) {
        var algoritmo = Algorithm.HMAC256(tokenSecret);
        var instanteDeExpiração = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
        try {
            return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuário.getEmail())
                .withExpiresAt(instanteDeExpiração)
                .sign(algoritmo);
        }
        catch (JWTCreationException exceção){
            throw new RuntimeException("Erro ao gerar token.", exceção);
        }
    }
    
    public String subject(String token) {
        var algoritmo = Algorithm.HMAC256(tokenSecret);
    	try {
    		return JWT.require(algoritmo)
    			.withIssuer(ISSUER)
    			.build()
                .verify(token)
                .getSubject();
    	}
    	catch (JWTVerificationException exceção) {
    		throw new RuntimeException("Token inválido.", exceção);
    	}
    }

    public boolean éVálido(String token) {
        var algoritmo = Algorithm.HMAC256(tokenSecret);
    	try {
    		JWT.require(algoritmo)
        		.withIssuer(ISSUER)
    			.build()
                .verify(token);

            var subject = JWT.decode(token).getSubject();
            return usuaRepository.findByEmail(subject) != null;
    	}
    	catch (JWTVerificationException exceção) {
    		return false;
    	}
    }

}
