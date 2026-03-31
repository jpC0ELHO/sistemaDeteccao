package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.configs.jwtConfigurartion;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter {
    /* extends OncePerRequestFilter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header!=null&&header.startsWith("Bearer ")){
            String token=header.substring(7);
        }
        filterChain.doFilter(request,response);
    }*/
}
