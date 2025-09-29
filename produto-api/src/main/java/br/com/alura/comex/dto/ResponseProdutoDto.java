package br.com.alura.comex.dto;

import br.com.alura.comex.model.Produto;

public record ResponseProdutoDto(
    Long id,
    String nome,
    Double preco,
    String descricao,
    Integer quantidadeEmEstoque,
    Long idDaCategoria) {

    public ResponseProdutoDto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEmEstoque(), produto.getCategoria().getId());
    }
    
}
