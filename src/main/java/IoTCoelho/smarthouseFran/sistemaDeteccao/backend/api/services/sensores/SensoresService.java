package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.sensores;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.SensoresRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.SensoresResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.SensoresTipo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SensoresService {
    List<SensoresResponse>findSensoresList();
    Optional<SensoresResponse>findSensoresId(UUID uuid);
    void createSensores(SensoresRequest sensoresRequest);
    void updateSensores(UUID uuid,SensoresRequest sensoresRequest);
    void deleteSensores(UUID uuid);
    void atualizarSensores(UUID uuid,Map<SensoresTipo,Boolean>novosEstados);
    void atualizarRegioes(UUID uuid, Map<Regiao,Boolean>novasRegioes);
}
