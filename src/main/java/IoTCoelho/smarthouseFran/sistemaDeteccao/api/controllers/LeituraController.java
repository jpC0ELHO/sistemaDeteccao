package IoTCoelho.smarthouseFran.sistemaDeteccao.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LeituraRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LeituraResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.leitura.LeituraService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/leitura/v1")
@AllArgsConstructor
public class LeituraController {
    private final LeituraService leituraSerivce;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeituraResponse>>findLeituraList(){
        return ResponseEntity.status(HttpStatus.OK).body(leituraSerivce.findLeituraList());
    }
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<LeituraResponse>>findLeituraId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(leituraSerivce.findLeituraId(id));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createLeitura(@RequestBody LeituraRequest leituraRequest){
        leituraSerivce.createLeitura(leituraRequest);
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateLeitura(@PathVariable UUID id,@RequestBody LeituraRequest leituraRequest){
        leituraSerivce.updateLeitura(id,leituraRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeitura(@PathVariable UUID id){
        leituraSerivce.deleteLeitura(id);
    }
}
