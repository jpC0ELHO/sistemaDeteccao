package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Device;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonPropertyOrder({
        "uuid",
        "createdBy",
        "lastModifiedBy",
        "createdAt",
        "updatedAt",
        "nomeDispositivo",
        "tipoDispositivo",
        "ipPermitido",
        "status",
        "ultimoAcesso",
        "chaveDeAcesso"
})
public record DeviceResponse(
        UUID uuid,
        String createdBy,
        String lastModifiedBy,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @JsonSerialize(using = DateSerializer.class)
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @JsonSerialize(using = DateSerializer.class)
        LocalDateTime updatedAt,
        String nomeDispositivo,
        String tipoDispositivo,
        String ipPermitido,
        Boolean status,
        LocalDateTime ultimoAcesso,
        String chaveDeAcesso
) {
    public static DeviceResponse toResponse(Device device){
        if (device==null){
            return null;
        }
        return new DeviceResponse(
                device.getUuid(),
                device.getCreatedBy(),
                device.getLastModifiedBy(),
                device.getCreatedAt(),
                device.getUpdatedAt(),
                device.getNomeDispositivo(),
                device.getTipoDispositivo(),
                device.getIpPermitido(),
                device.getStatus(),
                device.getUltimoAcesso(),
                device.getChaveDeAcesso()
        );
    }
}
