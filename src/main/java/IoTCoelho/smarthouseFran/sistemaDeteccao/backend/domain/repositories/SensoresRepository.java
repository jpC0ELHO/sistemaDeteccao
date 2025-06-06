package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Sensores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SensoresRepository extends JpaRepository<Sensores, UUID> {
    Optional<Sensores>findById(UUID uuid);
    Optional<Sensores> findByNome(String nome);
}
