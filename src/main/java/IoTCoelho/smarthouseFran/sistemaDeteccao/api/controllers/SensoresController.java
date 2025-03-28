package IoTCoelho.smarthouseFran.sistemaDeteccao.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.SensoresRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.SensoresResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.sensores.SensoresService;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.SensoresTipo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/sensores/v1")
@AllArgsConstructor
public class SensoresController {
    private final SensoresService sensoresService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SensoresResponse>>findSensoresList(){
        return ResponseEntity.status(HttpStatus.OK).body(sensoresService.findSensoresList());
    }
    @GetMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<SensoresResponse>>findSensoresId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(sensoresService.findSensoresId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createSensores(@RequestBody SensoresRequest sensoresRequest){
        sensoresService.createSensores(sensoresRequest);
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updatedSensores(@PathVariable UUID id,@RequestBody SensoresRequest sensoresRequest){
        sensoresService.updateSensores(id,sensoresRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensores(@PathVariable UUID id){
        sensoresService.deleteSensores(id);
    }

    @PatchMapping(value = "/{id}/sensores-tipo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>atualizarSensoresTipo(@PathVariable UUID id, @RequestBody Map<SensoresTipo,Boolean>novosEstados){
        sensoresService.atualizarSensores(id,novosEstados);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(value = "/{id}/regiao",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>atualizarRegiao(@PathVariable UUID id, @RequestBody Map<Regiao,Boolean>novasRegioes){
        sensoresService.atualizarRegioes(id,novasRegioes);
        return ResponseEntity.noContent().build();
    }

}
