package br.com.alura.comex.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/health")
public class HealthCheckController {
    
    @GetMapping    
    public String health() {
        return "ok";
    }

}
