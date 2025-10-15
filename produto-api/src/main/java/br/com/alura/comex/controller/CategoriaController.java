package br.com.alura.comex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import br.com.alura.comex.dto.EventoCategoriaDto;
import br.com.alura.comex.dto.RequestCategoriaDto;
import br.com.alura.comex.dto.ResponseCategoriaDto;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.KafkaProducerService;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private KafkaProducerService<EventoCategoriaDto> kafkaProducerService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid RequestCategoriaDto request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            var fieldError = bindingResult.getFieldError("nome");
            String mensagem = "";
            if (fieldError != null)
                mensagem = fieldError.getDefaultMessage();
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        var categoria = request.toCategoria();
        categoriaService.cadastrar(categoria);
        try {
            kafkaProducerService.enviar("CATEGORIA_CADASTRO", categoria.getId().toString(), new EventoCategoriaDto(categoria.getNome()));
        }
        catch (Exception exceção) {
            exceção.printStackTrace();
        }
        return new ResponseEntity<>(new ResponseCategoriaDto(categoria), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseCategoriaDto>> listar(@PageableDefault(size = 20) Pageable paginação) {
        return ResponseEntity.ok(categoriaService.listar(paginação));
    }
}
