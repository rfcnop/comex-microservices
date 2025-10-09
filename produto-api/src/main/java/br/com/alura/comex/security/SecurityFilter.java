package br.com.alura.comex.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.comex.http.UsuárioClient;
import br.com.alura.comex.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	UsuárioClient usuárioClient;

	@Autowired
	AutenticacaoService autenticacaoService;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
		var token = pegaToken(httpServletRequest);
		if (token != null) {
			boolean tokenÉVálido = false;
			try {
				tokenÉVálido = usuárioClient.tokenÉVálido(token);
			}
			catch (Throwable throwable) {
				tokenÉVálido = autenticacaoService.tokenÉVálido(token);
			}

			if (tokenÉVálido) {
				filterChain.doFilter(httpServletRequest, httpServletResponse);	
				return;
			}
		}
		
		httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
	}
	
	private String pegaToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null)
			return authorizationHeader.replace("Bearer ", "");
		return null;
	}
	
}
