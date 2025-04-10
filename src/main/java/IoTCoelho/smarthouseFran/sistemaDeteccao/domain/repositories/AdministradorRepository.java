package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdministradorRepository extends JpaRepository<Administrador, UUID> {
    Optional<Administrador>findByid(UUID uuid);
}
