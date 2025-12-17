package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeviceRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeviceResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.device.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/device",produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {
    private final DeviceService deviceService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeviceResponse>>findDeviceList(){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findDeviceList());
    }
    @GetMapping(value = "/{id}"
            ,produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<DeviceResponse>>findDeviceId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findDeviceId(id));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDevice(@RequestBody DeviceRequest deviceRequest){
        deviceService.createDevice(deviceRequest);
    }
    @PutMapping(value = "/{id}"
            ,produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDevice(@PathVariable UUID id,@RequestBody DeviceRequest deviceRequest){
        deviceService.updateDevice(id,deviceRequest);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteDevice(@PathVariable UUID id){
        deviceService.deleteDevice(id);
    }
}
