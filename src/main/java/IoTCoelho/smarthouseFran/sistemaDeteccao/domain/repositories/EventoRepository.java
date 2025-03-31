package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
    Optional<Evento>findById(UUID uuid);
}
