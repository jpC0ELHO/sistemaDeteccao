package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.configs.jwtConfigurartion;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService {
    /*
    private final String SECRET="159748@Franserver";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }*/

}
