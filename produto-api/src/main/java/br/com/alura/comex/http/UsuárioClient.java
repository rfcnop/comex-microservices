package br.com.alura.comex.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import br.com.alura.comex.dto.RequestValidateDto;
import br.com.alura.comex.dto.ResponseValidateDto;

@Component
public class UsuárioClient {

    private final RestClient restClient;

    public UsuárioClient(RestClient.Builder restClientBuilder, @Value("${token-validation.base-url}") String baseUrl) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build(); 
    }
    
    public boolean tokenÉVálido(String token) {
        var resposta = restClient.post()
            .uri("/api/validate")
            .contentType(MediaType.APPLICATION_JSON)
            .body(new RequestValidateDto(token))
            .retrieve()
            .body(ResponseValidateDto.class);
        return resposta.válido();
    }

}
