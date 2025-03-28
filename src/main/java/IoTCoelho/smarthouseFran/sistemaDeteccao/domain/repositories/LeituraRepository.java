package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LeituraRepository extends JpaRepository<Leitura, UUID> {
    Optional<Leitura>findById(UUID uuid);
}
