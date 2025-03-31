package IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Evento;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Localizacao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.EventoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","local","descricao","horarioEvento","eventoTipo","sensor"
        ,"createdBy","lastModifiedBy","createdAt","updatedAt"})
public record EventoResponse(
        UUID uuid,
        Localizacao local,
        String descricao,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime horarioEvento,
        Map<EventoTipo,Boolean>eventoTipo,
        Sensores sensor,
        String createdBy,
        String lastModifiedBy,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime updatedAt
) {
    public static EventoResponse toResponse(Evento evento){
        if (evento==null){
            return null;
        }
        return new EventoResponse(
                evento.getUuid(),
                evento.getLocal(),
                evento.getDescricao(),
                evento.getHorarioEvento(),
                evento.getEventoTipo(),
                evento.getSensor(),
                evento.getCreatedBy(),
                evento.getLastModifiedBy(),
                evento.getCreatedAt(),
                evento.getUpdatedAt()
        );
    }
}
