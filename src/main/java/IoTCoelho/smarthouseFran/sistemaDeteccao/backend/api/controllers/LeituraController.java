package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.LeituraRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.LeituraResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.leitura.LeituraService;
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
@RequestMapping(value = "/leitura/v1")
@AllArgsConstructor
public class LeituraController {
    private final LeituraService leituraService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeituraResponse>>findLeituraList(){
        return ResponseEntity.status(HttpStatus.OK).body(leituraService.findLeituraList());
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<LeituraResponse>>findLeituraId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(leituraService.findLeituraId(id));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createLeitura(@RequestBody LeituraRequest leituraRequest){
        leituraService.createLeitura(leituraRequest);
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateLeitura(@PathVariable UUID id,@RequestBody LeituraRequest leituraRequest){
        leituraService.updateLeitura(id,leituraRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeitura(@PathVariable UUID id){
        leituraService.deleteLeitura(id);
    }
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> processarLeitura(@RequestBody LeituraRequest leituraRequest) {
        leituraService.processarLeitura(leituraRequest);
        Map<String, String> response = Map.of("mensagem", "Leitura processada com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
