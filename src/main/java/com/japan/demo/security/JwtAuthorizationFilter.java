package com.japan.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    private static final long EXPIRATION_TIME = 864_000_000;

    private final String secretKey;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String secretKey) {
        setAuthenticationManager(authenticationManager);
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Map body = new HashMap<>();

        try {
            body = new ObjectMapper().readValue(request.getInputStream(), HashMap.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(body.get("username"), body.get("password")));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        new ObjectMapper().writeValue(response.getWriter(), responseBody);
    }

}
