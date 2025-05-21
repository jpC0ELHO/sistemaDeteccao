package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario>findById(UUID uuid);
    Optional<Usuario>findByName(String username);
}
