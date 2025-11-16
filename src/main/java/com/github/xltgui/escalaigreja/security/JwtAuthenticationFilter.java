package com.github.xltgui.escalaigreja.security;

import com.github.xltgui.escalaigreja.domain.jwt.JwtService;
import com.github.xltgui.escalaigreja.domain.user.UserEntity;
import com.github.xltgui.escalaigreja.domain.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getRequestURI();

        System.out.println("🔐 JwtAuthenticationFilter - Request Path: " + requestPath);
        System.out.println("🔐 JwtAuthenticationFilter - Authorization Header: " + request.getHeader("Authorization"));

        // Endpoints públicos - não requerem autenticação
        if (requestPath.contains("/auth/login") ||
                requestPath.contains("/users/register") ||
                requestPath.contains("/users/confirm") ||
                requestPath.contains("/test/")) {
            System.out.println("✅ JwtAuthenticationFilter - Endpoint público, ignorando autenticação");
            filterChain.doFilter(request, response);
            return;
        }

        String token = recoverToken(request);

        if(token != null){
            System.out.println("🔐 JwtAuthenticationFilter - Token recuperado: " + token.substring(0, Math.min(20, token.length())) + "...");

            try {
                String username = tokenService.validateToken(token);
                System.out.println("🔐 JwtAuthenticationFilter - Username extraído: " + username);

                if(username != null && !username.isEmpty()) {
                    UserEntity user = userService.findByUsername(username);
                    System.out.println("🔐 JwtAuthenticationFilter - Usuário encontrado: " + user.getUsername());
                    System.out.println("🔐 JwtAuthenticationFilter - Authorities: " + user.getAuthorities());

                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    System.out.println("✅ JwtAuthenticationFilter - Autenticação configurada com sucesso para: " + username);
                } else {
                    System.out.println("❌ JwtAuthenticationFilter - Username é nulo ou vazio");
                }
            } catch (Exception e) {
                System.err.println("❌ JwtAuthenticationFilter - Erro ao validar token: " + e.getMessage());
                // Não configuramos autenticação, a requisição falhará com 401
            }
        } else {
            System.out.println("❌ JwtAuthenticationFilter - Token não encontrado no header");
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        System.out.println("🔐 JwtAuthenticationFilter - Header Authorization: " + authHeader);

        // CORREÇÃO: usar OR em vez de AND
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            System.out.println("❌ JwtAuthenticationFilter - Header Authorization ausente ou inválido");
            return null;
        }

        String token = authHeader.substring(7); // removes "Bearer "
        System.out.println("✅ JwtAuthenticationFilter - Token extraído do header");
        return token;
    }
}