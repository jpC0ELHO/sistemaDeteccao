package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.configs;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.LoginRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.configs.jwtConfigurartion.JwtService;
@RestController
@RequestMapping("/auth")
public class AuthController {
    /*
    private Usuario usuario;
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        // 1. Validar credenciais (mock ou DB)
        if (!usuario.getUsername().equals(request.username()) ||
                !usuario.getPassword().equals(request.password())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        // 2. Gerar token
        return jwtService.generateToken(request.username());
    }*/
}
