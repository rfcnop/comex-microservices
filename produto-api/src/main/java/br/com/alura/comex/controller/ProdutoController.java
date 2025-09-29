package br.com.alura.comex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import br.com.alura.comex.dto.RequestProdutoDto;
import br.com.alura.comex.dto.ResponseProdutoDto;
import br.com.alura.comex.exception.ValidaçãoException;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Transactional
    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid RequestProdutoDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var mensagem = new StringBuilder();            
            bindingResult.getAllErrors().forEach(
                erro -> mensagem.append(erro.getDefaultMessage() + "; ")
            );
            return new ResponseEntity<>(mensagem.toString(), HttpStatus.BAD_REQUEST);
        }
        
        var categoria = categoriaService.buscaPorId(request.idDaCategoria());
        if (categoria == null)
            throw new ValidaçãoException("O ID de categoria informado ao cadastrar produto é inválido.");
        var produto = request.toProduto(categoria);
        produtoService.cadastrar(produto);

        return new ResponseEntity<>(new ResponseProdutoDto(produto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseProdutoDto>> listar(@PageableDefault(size = 20) Pageable paginação) {
        return ResponseEntity.ok(produtoService.listar(paginação));
    }
    
}
