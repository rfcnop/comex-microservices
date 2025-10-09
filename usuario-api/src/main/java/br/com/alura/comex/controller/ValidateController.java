package br.com.alura.comex.controller;

import jakarta.validation.Valid;
import br.com.alura.comex.dto.RequestValidateDto;
import br.com.alura.comex.dto.ResponseValidateDto;
import br.com.alura.comex.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validate")
public class ValidateController {

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<ResponseValidateDto> validaToken(@RequestBody @Valid RequestValidateDto request) {
        if (request.token() == null)
            return ResponseEntity.badRequest().build(); 
        var válido = tokenService.éVálido(request.token());
        return ResponseEntity.ok(new ResponseValidateDto(válido));
    }

}
