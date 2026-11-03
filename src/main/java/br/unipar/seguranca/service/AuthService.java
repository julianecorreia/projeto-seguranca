package br.unipar.seguranca.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import br.unipar.seguranca.dto.LoginRequest;
import br.unipar.seguranca.dto.LoginResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserDetailsService users;
    private final PasswordEncoder passwords;
    private final JwtEncoder jwtEncoder;

    public AuthService(UserDetailsService users, PasswordEncoder passwords, JwtEncoder jwtEncoder) {
        this.users = users;
        this.passwords = passwords;
        this.jwtEncoder = jwtEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        try {
            var user = users.loadUserByUsername(request.usuario());
            if (!passwords.matches(request.senha(), user.getPassword())) {
                throw new BadCredentialsException("Credenciais inválidas");
            }
        } catch (UsernameNotFoundException | IllegalArgumentException ex) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        var now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .subject(request.usuario())
                .expiresAt(now.plus(15, ChronoUnit.MINUTES))
                .build();
        var header = JwsHeader.with(MacAlgorithm.HS256).build();
        var token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return new LoginResponse(token, "Bearer", 900);
    }
}
