package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeteccaoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.EventoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.EventoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.evento.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/evento/v1")
@AllArgsConstructor
public class EventoController {
    private final EventoService eventoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventoResponse>>findEventoList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(eventoService.findEventoList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<EventoResponse>>findEventoId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.FOUND).body(eventoService.findEventoId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvento(@RequestBody EventoRequest eventoRequest){
        eventoService.createEvento(eventoRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateEvento(@PathVariable UUID id,@RequestBody EventoRequest eventoRequest){
        eventoService.updateEvento(id,eventoRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable UUID id){
        eventoService.deleteEvento(id);
    }
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventoResponse>detecEventoLeitura(@RequestBody DeteccaoRequest deteccaoRequest){
        EventoResponse eventoResponse=eventoService.detecEventoLeitura(deteccaoRequest.getLeitura(),deteccaoRequest.getEventoTipo());
        return ResponseEntity.ok(eventoResponse);
    }
}
