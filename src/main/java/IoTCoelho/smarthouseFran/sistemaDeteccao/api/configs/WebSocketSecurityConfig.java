package IoTCoelho.smarthouseFran.sistemaDeteccao.api.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSocketSecurityConfig {
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeHttpRequests(auth->auth.requestMatchers("/ws/**").authenticated())
                .oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }
}
