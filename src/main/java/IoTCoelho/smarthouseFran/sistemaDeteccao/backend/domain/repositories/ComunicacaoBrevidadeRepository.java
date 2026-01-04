package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.ComunicacaoBrevidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ComunicacaoBrevidadeRepository extends JpaRepository<ComunicacaoBrevidade, UUID> {
    Optional<ComunicacaoBrevidade>findById(UUID uuid);
}
