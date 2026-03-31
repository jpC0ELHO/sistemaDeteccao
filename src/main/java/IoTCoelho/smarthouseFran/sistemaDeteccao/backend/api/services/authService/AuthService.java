package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.authService;


import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    /*
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void validar(String username, String password) {
        var usuario = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }
    }*/
}