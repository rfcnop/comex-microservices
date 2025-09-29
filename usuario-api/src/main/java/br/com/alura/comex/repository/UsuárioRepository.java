package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.Usuario;

public interface UsuárioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    boolean existsByEmail(String email);

}
