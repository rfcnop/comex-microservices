package br.com.alura.comex.dto;

import br.com.alura.comex.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestUsuárioDto(
    @NotNull(message = "O e-mail não pode ser nulo.")
    @Email(message = "O e-mail informado deve possuir formato válido.")
    @Size(max = 50, message = "O e-mail deve possuir no máximo 50 caracteres.")
    String email,
    
    @NotNull(message = "A senha não pode ser nula.")
    @Size(min = 3, max = 60, message = "A senha precisa ter no mínimo 3 caracteres e no máximo 60.")
    String senha)
{
    public Usuario toUsuário() {
        return new Usuario(email, senha);
    }
}
