package IoTCoelho.smarthouseFran.sistemaDeteccao.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.UsuarioRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.UsuarioResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/usuarios/v1")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioResponse>> findUsuarioList(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findUsuarioList());
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UsuarioResponse>> findUsuarioId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findUsuarioId(id));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUsuario(@RequestBody UsuarioRequest usuarioRequest){
        usuarioService.createUsuario(usuarioRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUsuario(@PathVariable UUID id,@RequestBody UsuarioRequest usuarioRequest){
        usuarioService.updateUsuario(id,usuarioRequest);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable UUID id){
        deleteUsuario(id);
    }
}
