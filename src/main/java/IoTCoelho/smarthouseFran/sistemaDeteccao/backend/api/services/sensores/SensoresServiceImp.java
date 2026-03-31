package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.sensores;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.sensores.SensorRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.sensores.SensorResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.devices.Sensor;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.SensoresTipo;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.exceptions.ModelIntegrityViolationException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories.SensoresRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.sensores.SensorRequest.toEntidade;

@Log4j2
@Service
@AllArgsConstructor
public class SensoresServiceImp implements SensoresService{
    private final SensoresRepository sensoresRespository;
    @Override
    public List<SensorResponse> findSensoresList() {
        try {
            var findSensoresList=sensoresRespository.findAll();
            if (findSensoresList.isEmpty()){
            log.info("List not found, try again or verify the error.");
            throw new ModelNotFoundException("List not found!");
            }
            return findSensoresList.stream().map(SensorResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error: {} ",e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public Optional<SensorResponse> findSensoresId(UUID uuid) {
        var findSensoresId=sensoresRespository.findById(uuid);
        if (findSensoresId.isEmpty()){
            log.warn("Id: {} not found",uuid);
            throw new ModelNotFoundException("ID not found!");
        }
        return findSensoresId.map(SensorResponse::toResponse);
    }

    @Override
    public void createSensores(SensorRequest sensorRequest) {
    var findSensoresByNome=sensoresRespository.findByNome(sensorRequest.nome());
    if (findSensoresByNome.isPresent()){
        log.warn("Sensor with name: {} already exists!", sensorRequest.nome());
        throw new ModelIntegrityViolationException("This name already exists!");
    }
    sensoresRespository.save(toEntidade(sensorRequest));
    }

    @Override
    public void updateSensores(UUID uuid, SensorRequest sensorRequest) {
        var findSensorById =
        sensoresRespository.findById(uuid).orElseThrow(() -> new ModelNotFoundException("ID not found!"));

        findSensorById.setNome(sensorRequest.nome());
        findSensorById.setAtivadoDesativado(sensorRequest.ativadoDesativado());
        findSensorById.setHorarioAcionamento(sensorRequest.horarioAcionamento());
        findSensorById.setMemoriaUsada(sensorRequest.memoriaUsada());
        findSensorById.setMemoriaDisponivel(sensorRequest.memoriaDisponivel());
        findSensorById.setValorDadosTransferencia(sensorRequest.valorDadosTransferencia());
        sensoresRespository.save(findSensorById);
    }

    @Override
    public void deleteSensores(UUID uuid) {
    try {
        var findSensoreId=sensoresRespository.findById(uuid).orElseThrow(()-> new ModelNotFoundException("ID not found!"));
        sensoresRespository.delete(findSensoreId);
    }catch (RuntimeException e){
        log.error("Error deleting sensor: {}", e.getMessage(), e);
        throw e;
    }
    }

    @Override
    public void atualizarSensores(UUID uuid,Map<SensoresTipo, Boolean> novosEstados) {
        Sensor sensor= sensoresRespository
                .findById(uuid)
                .orElseThrow(()->new ModelNotFoundException("ID: "+uuid+" not found!"));
        if (sensor.getSensorTipo()==null){
            sensor.setSensorTipo(new HashMap<>());
        }
        novosEstados.forEach(((sensoresTipo, estado) ->{
            sensor.getSensorTipo().put(sensoresTipo,estado);
        } ));
        sensoresRespository.save(sensor);
        log.info("Tipos de sensores com ID: {} atualizados com sucesso ",uuid);
    }

    @Override
    public void atualizarRegioes(UUID uuid,Map<Regiao, Boolean> novasRegioes) {
        Sensor sensor = sensoresRespository
                .findById(uuid)
                .orElseThrow(() -> new ModelNotFoundException("Sensor com ID " + uuid + " não encontrado"));

        if (sensor.getRegiao() == null) {
            sensor.setRegiao(new HashMap<>());
        }
        novasRegioes.forEach((regiao, estado) -> {
            sensor.getRegiao().put(regiao, estado);
        });
        sensoresRespository.save(sensor);
        log.info("Regiões do sensor {} atualizadas com sucesso.", uuid);
    }
}
