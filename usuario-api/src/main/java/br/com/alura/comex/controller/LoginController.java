package br.com.alura.comex.controller;

import jakarta.validation.Valid;
import br.com.alura.comex.amqp.AMQPConfiguration;
import br.com.alura.comex.dto.AutenticaçãoDto;
import br.com.alura.comex.dto.RequestUsuárioDto;
import br.com.alura.comex.dto.ResponseLoginDto;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.service.TokenService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<ResponseLoginDto> login(@RequestBody @Valid RequestUsuárioDto request) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.senha()));
        var usuário = (Usuario) authentication.getPrincipal();
        var token = tokenService.geraToken(usuário);
        var autenticaçãoDto = new AutenticaçãoDto(token, usuário);

        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_AUTENTICACAO, AMQPConfiguration.ROUTING_KEY_AUTENTICACAO, autenticaçãoDto);

        return ResponseEntity.ok(new ResponseLoginDto(token));
    }

}
