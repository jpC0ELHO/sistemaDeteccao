package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.sensores;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.sensores.SensorRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.sensores.SensorResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.SensoresTipo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SensoresService {
    List<SensorResponse>findSensoresList();
    Optional<SensorResponse>findSensoresId(UUID uuid);
    void createSensores(SensorRequest sensorRequest);
    void updateSensores(UUID uuid, SensorRequest sensorRequest);
    void deleteSensores(UUID uuid);
    void atualizarSensores(UUID uuid,Map<SensoresTipo,Boolean>novosEstados);
    void atualizarRegioes(UUID uuid, Map<Regiao,Boolean>novasRegioes);
}
