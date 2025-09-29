package br.com.alura.comex.controller;

import jakarta.validation.Valid;
import br.com.alura.comex.dto.RequestUsuárioDto;
import br.com.alura.comex.dto.ResponseUsuárioDto;
import br.com.alura.comex.service.UsuárioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuárioController {

    @Autowired
    private UsuárioService usuárioService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid RequestUsuárioDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var mensagem = new StringBuilder();            
            bindingResult.getAllErrors().forEach(
                erro -> mensagem.append(erro.getDefaultMessage() + "; ")
            );
            return new ResponseEntity<>(mensagem.toString(), HttpStatus.BAD_REQUEST);
        }

        var usuário = request.toUsuário();
        usuárioService.cadastrar(usuário);

        return new ResponseEntity<>(new ResponseUsuárioDto(usuário), HttpStatus.CREATED);
    }

}
