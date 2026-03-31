package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.devices.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SensoresRepository extends JpaRepository<Sensor, UUID> {
    Optional<Sensor>findById(UUID uuid);
    Optional<Sensor> findByNome(String nome);
}
