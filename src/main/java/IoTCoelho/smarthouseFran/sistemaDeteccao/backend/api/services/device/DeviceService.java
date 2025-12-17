package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.device;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeviceRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.DeviceResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceService {
    List<DeviceResponse>findDeviceList();
    Optional<DeviceResponse>findDeviceId(UUID uuid);
    void createDevice(DeviceRequest deviceRequest);
    void updateDevice(UUID uuid,DeviceRequest deviceRequest);
    void deleteDevice(UUID uuid);
}
