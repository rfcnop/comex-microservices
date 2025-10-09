package br.com.alura.comex.model;

import java.time.Instant;

import br.com.alura.comex.dto.TipoDeAutenticação;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Autenticacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String token;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private Instant instante;

    @Enumerated(EnumType.STRING)
    private TipoDeAutenticação tipo;

    public Autenticacao() {

    }

    public Autenticacao(String token, String nome, boolean ativo, TipoDeAutenticação tipo, Instant instante) {
        this.token = token;
        this.nome = nome;
        this.ativo = ativo;
        this.tipo = tipo;
        this.instante = instante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public TipoDeAutenticação getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeAutenticação tipo) {
        this.tipo = tipo;
    }

    public Instant getInstante() {
        return instante;
    }

    public void setDataHora(Instant instante) {
        this.instante = instante;
    }

}