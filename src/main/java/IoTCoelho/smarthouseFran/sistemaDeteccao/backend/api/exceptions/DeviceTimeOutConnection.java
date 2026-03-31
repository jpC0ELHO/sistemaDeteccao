package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.exceptions;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.communication.DeviceSession;

import java.net.ConnectException;

public class DeviceTimeOutConnection extends ConnectException {
    public DeviceTimeOutConnection(DeviceSession deviceSession, String message) {

        super("Device message : "+deviceSession.getDeviceMenssage()
                +" Time: "+deviceSession.getTempo()
                +" Error :"+message);
    }
}
