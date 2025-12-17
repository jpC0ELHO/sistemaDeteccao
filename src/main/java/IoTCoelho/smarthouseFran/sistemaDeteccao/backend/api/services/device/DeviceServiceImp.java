package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.device;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeviceRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeviceResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeviceRequest.toEntidade;

@AllArgsConstructor
@Service
@Log4j2
public class DeviceServiceImp implements DeviceService{

    private final DeviceRepository deviceRepository;
    @Override
    public List<DeviceResponse> findDeviceList() {
    try {
        var findDeviceList=deviceRepository.findAll();
        if (findDeviceList.isEmpty()){
            throw new ModelNotFoundException("Device list not Found!");
        }
        return findDeviceList.stream().map(DeviceResponse::toResponse).toList();
    }catch (RuntimeException e){
        log.info("Error inside code Device list: {}",e.getMessage());
        throw new RuntimeException("Error");
    }
    }

    @Override
    public Optional<DeviceResponse> findDeviceId(UUID uuid) {
        return deviceRepository.findById(uuid).map(DeviceResponse::toResponse).or(()->{
            log.error("Device id not found");
            throw new ModelNotFoundException("Device id :"+uuid+" not found");
        });
    }

    @Override
    public void createDevice(DeviceRequest deviceRequest) {
    deviceRepository.save(toEntidade(deviceRequest));
    }

    @Override
    public void updateDevice(UUID uuid, DeviceRequest deviceRequest) {
    var findDevice=deviceRepository
            .findById(uuid)
            .orElseThrow(()-> new ModelNotFoundException("ID :"+uuid+" not found"));
    findDevice.setChaveDeAcesso(deviceRequest.chaveDeAcesso());
    findDevice.setTipoDispositivo(deviceRequest.tipoDispositivo());
    findDevice.setIpPermitido(deviceRequest.ipPermitido());
    findDevice.setStatus(deviceRequest.status());
    findDevice.setUltimoAcesso(deviceRequest.ultimoAcesso());
    findDevice.setChaveDeAcesso(deviceRequest.chaveDeAcesso());
    deviceRepository.save(findDevice);
    }

    @Override
    public void deleteDevice(UUID uuid) {
        try{
            var findDevice=deviceRepository
                    .findById(uuid)
                    .orElseThrow(()-> new ModelNotFoundException("ID :"+uuid+" not found"));
            deviceRepository.delete(findDevice);
        }catch (RuntimeException e){
            log.error("Error: {} inside delete device!",e.getMessage());
            throw e;
        }

    }
}
