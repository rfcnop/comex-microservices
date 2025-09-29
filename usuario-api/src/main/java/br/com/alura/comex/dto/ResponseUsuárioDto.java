package br.com.alura.comex.dto;

import br.com.alura.comex.model.Usuario;

public record ResponseUsuárioDto(
    Long id,
    String email) {
    
    public ResponseUsuárioDto(Usuario usuário) {
        this(usuário.getId(), usuário.getEmail());
    }
    
}
