package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.LocalizacaoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.LocalizacaoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.localizacao.LocalizacaoService;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Regiao;
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
@RequestMapping(value = "/localizacao/v1")
@AllArgsConstructor
public class LocalizacaoController {
    private final LocalizacaoService localizacaoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocalizacaoResponse>>findLocalizacaoList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(localizacaoService.findLocalizacaoList());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<LocalizacaoResponse>>findLocalizacaoId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.FOUND).body(localizacaoService.findLocalizacaoId(id));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createLocalizacao(@RequestBody LocalizacaoRequest localizacaoRequest){
        localizacaoService.createLocalizacao(localizacaoRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLocalizacao(@PathVariable UUID id,@RequestBody LocalizacaoRequest localizacaoRequest){
        localizacaoService.updateLocalizacao(id,localizacaoRequest);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoocalizacao(@PathVariable UUID id){
        localizacaoService.deleteLocalizacao(id);
    }

    @PatchMapping(value = "/{id}/regiao")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarRegiao(@PathVariable UUID id, @RequestBody Map<Regiao,Boolean>novasRegioes){
        localizacaoService.atualizarRegiao(id,novasRegioes);
    }
}
