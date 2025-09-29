package br.com.alura.comex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuárioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuárioService {

    @Autowired
    private UsuárioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void cadastrar(Usuario usuário) {
        if (usuário == null)
            return;
        if (repository.existsByEmail(usuário.getEmail()))
            return;

        usuário.setSenha(passwordEncoder.encode(usuário.getSenha()));
        repository.save(usuário);
    }
  
}
