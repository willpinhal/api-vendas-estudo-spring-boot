package com.github.willpinhal.apivendas.apivendas.security.jwt;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;


@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {

        long tempoExpiracao = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(tempoExpiracao);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("emailusuario", "usuario@gmail.com");
//        claims.put("roles","admin");

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                //.setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime dataToken = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(dataToken);

        } catch (Exception ex) {
            return false;
        }
    }

    public String obterUsuarioLogado(String token) {

        return (String) obterClaims(token).getSubject();
    }

//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(ApiVendasApplication.class);
//        JwtService service = context.getBean(JwtService.class);
//        Usuario usuario = Usuario.builder().login("fulano").build();
//        String token = service.gerarToken(usuario);
//        System.out.println("Token gerado -> " + token);
//
//        String login = service.obterUsuarioLogado(token);
//        System.out.println("Usuário logado -> " + login);
//    }
}
