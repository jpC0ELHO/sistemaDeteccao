package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Device;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DeviceRequest(
        @NotBlank
        String nomeDispositivo,
        @NotBlank
        String tipoDispositivo,
        @NotBlank
        String ipPermitido,
        Boolean status,
        LocalDateTime ultimoAcesso,
        @NotBlank
        String chaveDeAcesso
) {
    public static Device toEntidade(DeviceRequest deviceRequest){
        if (deviceRequest==null){
            return null;
        }
        return new Device(
                deviceRequest.nomeDispositivo,
                deviceRequest.tipoDispositivo,
                deviceRequest.ipPermitido,
                deviceRequest.status,
                deviceRequest.ultimoAcesso,
                deviceRequest.chaveDeAcesso
        );
    }
}
