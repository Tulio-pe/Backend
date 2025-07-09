package com.acme.tallerazo.iam.infrastructure.authorization.sfs.pipeline;

import com.acme.tallerazo.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private final BearerTokenService tokenService;
    private final UserDetailsService userDetailsService;

    // 🔥 PATHS QUE NO REQUIEREN AUTENTICACIÓN JWT
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/api/v1/authentication",
            "/api/v1/workshops",        // 🔥 AGREGAR WORKSHOPS
            "/api/v1/services",
            "/api/v1/regions",
            "/api/v1/districts",
            "/api/v1/provinces",
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-resources",
            "/webjars"
    );

    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        // 🔥 SI LA RUTA ESTÁ EXCLUIDA, SALTAR AUTENTICACIÓN JWT
        if (EXCLUDED_PATHS.stream().anyMatch(requestPath::startsWith)) {
            logger.info("🚀 Skipping JWT authentication for: " + requestPath);
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = getTokenFromRequest(request);
            if (StringUtils.hasText(token) && tokenService.validateToken(token)) {
                String username = tokenService.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception exception) {
            logger.error("Cannot set user authentication: {}");
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
