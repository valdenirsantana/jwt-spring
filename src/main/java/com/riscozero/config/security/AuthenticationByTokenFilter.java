package com.riscozero.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.riscozero.model.User;
import com.riscozero.repository.UserRepository;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	private UserRepository repository;

	public AuthenticationByTokenFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		if (tokenService.isValidToken(token)) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long idUser = tokenService.getIdUser(token);
		User user = repository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
