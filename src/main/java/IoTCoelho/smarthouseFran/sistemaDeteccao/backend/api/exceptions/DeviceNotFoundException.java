package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.exceptions;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.devices.Device;

import lombok.Getter;

@Getter
public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(Device device,String message){
        super("Device type : "+device.getTipoDispositivo()
                +" Device name: "+device.getNomeDispositivo()
                +" Error : "+message);
    }
}
