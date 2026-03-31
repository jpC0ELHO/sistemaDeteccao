package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.comunicacaoBrevidade.ComunicacaoBrevidadeRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.comunicacaoBrevidade.ComunicacaoBrevidadeResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.comunicacaoBrevidade.ComunicacaoBrevidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/comunicacao-brevidade",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ComunicacaoBrevidadeController {

    private final ComunicacaoBrevidadeService comunicacaoBrevidadeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComunicacaoBrevidadeResponse>>findComunicacaoList(){
        return ResponseEntity.status(HttpStatus.OK).body(comunicacaoBrevidadeService.findComunicacaoList());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ComunicacaoBrevidadeResponse>>findComunicacaoId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(comunicacaoBrevidadeService.findComunicacaoId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createComunicacaoBrevidade(@RequestBody ComunicacaoBrevidadeRequest comunicacaoBrevidadeRequest){
        comunicacaoBrevidadeService.createComunicacaoBrevidade(comunicacaoBrevidadeRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateComunicacaoBrevidade(@PathVariable UUID id,@RequestBody ComunicacaoBrevidadeRequest comunicacaoBrevidadeRequest){
        comunicacaoBrevidadeService.updateComunicacaoBrevidade(id,comunicacaoBrevidadeRequest);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComunicacaoBrevidade(@PathVariable UUID id){
        comunicacaoBrevidadeService.deleteComunicacaoBrevidade(id);
    }

}
