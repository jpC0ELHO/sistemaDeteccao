package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.communication.ComunicacaoBrevidade;
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
        "macAddress",
        "latitudeAtual",
        "longitudeAtual",
        "latitudeReferencia",
        "longitudeReferencia",
        "distanciaCalculadaKm",
        "foraDoPerimetro",
        "brevidadeConfirmada",
        "enviadoAoSmartwatch",
        "dataEnvio"
})
public record ComunicacaoBrevidadeResponse(
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
        String macAddress,
        Double latitudeAtual,
        Double longitudeAtual,
        Double latitudeReferencia,
        Double longitudeReferencia,
        Double distanciaCalculadaKm,
        Boolean foraDoPerimetro,
        Boolean brevidadeConfirmada,
        Boolean enviadoAoSmartwatch,
        LocalDateTime dataEnvio
) {
    public static ComunicacaoBrevidadeResponse toResponse(ComunicacaoBrevidade comunicacaoBrevidade){
        if (comunicacaoBrevidade==null){
            return null;
        }
        return new ComunicacaoBrevidadeResponse(
                comunicacaoBrevidade.getUuid(),
                comunicacaoBrevidade.getCreatedBy(),
                comunicacaoBrevidade.getLastModifiedBy(),
                comunicacaoBrevidade.getCreatedAt(),
                comunicacaoBrevidade.getUpdatedAt(),
                comunicacaoBrevidade.getMacAddress(),
                comunicacaoBrevidade.getLatitudeAtual(),
                comunicacaoBrevidade.getLongitudeAtual(),
                comunicacaoBrevidade.getLatitudeReferencia(),
                comunicacaoBrevidade.getLongitudeReferencia(),
                comunicacaoBrevidade.getDistanciaCalculadaKm(),
                comunicacaoBrevidade.getForaDoPerimetro(),
                comunicacaoBrevidade.getBrevidadeConfirmada(),
                comunicacaoBrevidade.getEnviadoAoSmartwatch(),
                comunicacaoBrevidade.getDataEnvio()
        );
    }
}
