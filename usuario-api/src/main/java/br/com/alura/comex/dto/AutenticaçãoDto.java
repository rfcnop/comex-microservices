package br.com.alura.comex.dto;

import java.time.Instant;

import br.com.alura.comex.model.Usuario;

public record AutenticaçãoDto(
    String token,
    String nome,
    boolean ativo,
    TipoDeAutenticação tipo,
    Instant instante) {

    public AutenticaçãoDto(String token, Usuario usuário) {
        this(token, usuário.getUsername(), true, TipoDeAutenticação.AUTH, Instant.now());
    }

}
