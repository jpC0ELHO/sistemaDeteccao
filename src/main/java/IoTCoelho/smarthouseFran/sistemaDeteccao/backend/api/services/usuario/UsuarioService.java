package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.usuario;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.UsuarioRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.UsuarioResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    List<UsuarioResponse>findUsuarioList();
    Optional<UsuarioResponse> findUsuarioId(UUID uuid);
    void createUsuario(UsuarioRequest usuarioRequest);
    void updateUsuario(UUID uuid,UsuarioRequest usuarioRequest);
    void deleteUsuario(UUID uuid);
    //user details
    public UserDetails loadUserByUsername(String username);
}
