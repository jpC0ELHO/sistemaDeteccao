package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Localizacao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, UUID> {
    Optional<Localizacao>findById(UUID uuid);
    Optional<Localizacao>findByRegiao(Map<Regiao,Boolean>novasRegioes);
}
