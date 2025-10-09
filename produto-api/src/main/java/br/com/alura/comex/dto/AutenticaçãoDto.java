package br.com.alura.comex.dto;

import java.time.Instant;

import br.com.alura.comex.model.Autenticacao;

public record AutenticaçãoDto(
    String token,
    String nome,
    boolean ativo,
    TipoDeAutenticação tipo,
    Instant instante) {

    public Autenticacao toAutenticacao() {
        return new Autenticacao(token, nome, ativo, tipo, instante);
    }
}
