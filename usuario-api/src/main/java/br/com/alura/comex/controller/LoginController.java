package br.com.alura.comex.controller;

import jakarta.validation.Valid;
import br.com.alura.comex.dto.RequestUsuárioDto;
import br.com.alura.comex.dto.ResponseLoginDto;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<ResponseLoginDto> login(@RequestBody @Valid RequestUsuárioDto request) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.senha()));
        var token = tokenService.geraToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseLoginDto(token));
    }

}
