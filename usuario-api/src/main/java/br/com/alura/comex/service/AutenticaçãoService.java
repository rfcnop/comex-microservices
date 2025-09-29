package br.com.alura.comex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.comex.repository.UsuárioRepository;

@Service
public class AutenticaçãoService implements UserDetailsService {

    @Autowired
    private UsuárioRepository usuárioRepository;

    @Override
    public UserDetails loadUserByUsername(String nomeDoUsuário) throws UsernameNotFoundException {
        return usuárioRepository.findByEmail(nomeDoUsuário);
    }
}
