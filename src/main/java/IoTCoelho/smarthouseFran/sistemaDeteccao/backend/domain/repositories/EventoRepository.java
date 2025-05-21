package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
    Optional<Evento>findById(UUID uuid);

}
