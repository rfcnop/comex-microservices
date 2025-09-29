package br.com.alura.comex.dto;

import br.com.alura.comex.model.Categoria;

public record ResponseCategoriaDto(
    Long id,
    String nome) {
    
    public ResponseCategoriaDto(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
    
}
