package com.example.fitness.security;

import com.example.fitness.service.Extraction.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (request.getServletPath().equals("/login") || request.getServletPath().equals("/register")) {
            chain.doFilter(request, response);
            return;
        }

        // Vérifier si l'en-tête Authorization est présent et commence par "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("Authorization Header: " + authHeader);

        // Extraire le token JWT et le nom d'utilisateur
        jwt = authHeader.substring(7);

        try {
            username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println("Extracted Username: " + username);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Vérification de la validité du token
                if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                    // Créer un token d'authentification
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Ajouter des détails d'authentification (par exemple, l'adresse IP)
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Stocker l'authentification dans le contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            System.out.println("JWT invalide ou expiré : " + e.getMessage());
        }

        chain.doFilter(request, response);
    }


}
