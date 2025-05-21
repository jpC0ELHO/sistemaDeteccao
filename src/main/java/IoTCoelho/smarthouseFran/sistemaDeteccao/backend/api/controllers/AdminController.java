package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.AdministradorRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.AdministradorResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.administrador.AdministradorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admin/v1")
@AllArgsConstructor
public class AdminController {
    private final AdministradorService administradorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdministradorResponse>>findAdminList(){
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.findAdministradorList());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<AdministradorResponse>>findAdminId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.findAdministradorId(uuid));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAdmin(@RequestBody AdministradorRequest administradorRequest){
        administradorService.createAdministrador(administradorRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateAdmin(@PathVariable UUID uuid,@RequestBody AdministradorRequest administradorRequest){
        administradorService.updateAdministrador(uuid,administradorRequest);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable UUID uuid){
        administradorService.deleteAdministrador(uuid);
    }
}
