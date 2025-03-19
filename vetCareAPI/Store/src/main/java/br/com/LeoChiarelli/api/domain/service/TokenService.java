package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.model.Usuario;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String ISSUER = "API VETCARE+";

    @Value("${app.security.token.secret")
    private String secret;

    public String gerarToken(@NotNull Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withClaim("nome", usuario.getNome())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token jwt",e);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new ValidacaoException("Token Inválido ou já expirado");
        }
    }

    public Long getIdUsuarioDoToken(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            var decodeJWT = JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT);
            System.out.println("DecodeJWT: " + decodeJWT.getClaim("id").asLong());
            return decodeJWT.getClaim("id").asLong();
        } catch (JWTVerificationException e){
            throw new ValidacaoException("Token inválido ou já expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
