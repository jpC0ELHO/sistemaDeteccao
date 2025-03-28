package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.SensoresRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.SensoresResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.SensoresTipo;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelIntegrityViolationException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories.SensoresRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.SensoresRequest.toEntidade;

@Log4j2
@Service
@AllArgsConstructor
public class SensoresServiceImp implements SensoresService{
    private final SensoresRepository sensoresRespository;
    @Override
    public List<SensoresResponse> findSensoresList() {
        try {
            var findSensoresList=sensoresRespository.findAll();
            if (findSensoresList.isEmpty()){
            log.info("List not found, try again or verify the error.");
            throw new ModelNotFoundException("List not found!");
            }
            return findSensoresList.stream().map(SensoresResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error: {} ",e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public Optional<SensoresResponse> findSensoresId(UUID uuid) {
        var findSensoresId=sensoresRespository.findById(uuid);
        if (findSensoresId.isEmpty()){
            log.warn("Id: {} not found",uuid);
            throw new ModelNotFoundException("ID not found!");
        }
        return findSensoresId.map(SensoresResponse::toResponse);
    }

    @Override
    public void createSensores(SensoresRequest sensoresRequest) {
    var findSensoresByNome=sensoresRespository.findByName(sensoresRequest.nome());
    if (findSensoresByNome.isPresent()){
        log.warn("Sensor with name: {} already exists!",sensoresRequest.nome());
        throw new ModelIntegrityViolationException("This name already exists!");
    }
    sensoresRespository.save(toEntidade(sensoresRequest));
    }

    @Override
    public void updateSensores(UUID uuid, SensoresRequest sensoresRequest) {
        var findSensorById =
        sensoresRespository.findById(uuid).orElseThrow(() -> new ModelNotFoundException("ID not found!"));

        findSensorById.setNome(sensoresRequest.nome());
        findSensorById.setAtivadoDesativado(sensoresRequest.ativadoDesativado());
        findSensorById.setHorarioAcionamento(sensoresRequest.horarioAcionamento());
        findSensorById.setMemoriaUsada(sensoresRequest.memoriaUsada());
        findSensorById.setMemoriaDisponivel(sensoresRequest.memoriaDisponivel());
        findSensorById.setValorDadosTransferencia(sensoresRequest.valorDadosTransferencia());
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
        Sensores sensor= sensoresRespository
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
        Sensores sensor = sensoresRespository
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
