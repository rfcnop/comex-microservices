package br.com.alura.comex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.comex.dto.ResponseProdutoDto;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void cadastrar(Produto produto) {
        if (produto == null)
            return;
        repository.save(produto);
    }

    public Page<ResponseProdutoDto> listar(Pageable paginação) {
        return repository.findAll(paginação).map(ResponseProdutoDto::new);
    }
  
}
