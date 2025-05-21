package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Evento;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Localizacao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.EventoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EventoRequest (
        @NotNull
        Localizacao local,
        @NotBlank
        String descricao,
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime horarioEvento,
        @NotNull
        Map<EventoTipo,Boolean> eventoTipo,
        @NotNull
        Sensores sensor,
        @NotNull Leitura leitura
){
    public static Evento toEntidade(EventoRequest eventoRequest){
        if (eventoRequest==null){
            return null;
        }
        return new Evento(
                eventoRequest.local,
                eventoRequest.descricao,
                eventoRequest.horarioEvento,
                eventoRequest.eventoTipo,
                eventoRequest.sensor,
                eventoRequest.leitura
        );
    }
}
