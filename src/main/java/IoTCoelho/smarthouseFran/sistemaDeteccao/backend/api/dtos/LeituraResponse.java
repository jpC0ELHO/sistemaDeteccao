package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Evento;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Localizacao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.DeteccaoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","deteccao_sim_nao","horarioDetec","local",
        "sensores","valorAferido","eventos","createdBy","lastModifiedBy","createdAt","updatedAt"})
public record LeituraResponse(
        UUID uuid,
        boolean deteccao_sim_nao,
        DeteccaoTipo deteccaoTipo,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @JsonSerialize(using = DateSerializer.class)
        LocalDateTime horarioDetec,
        Localizacao local,
        Sensores sensores,
        int valorAferido,
        List<Evento> eventos,
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
        LocalDateTime updatedAt
) {
    public static LeituraResponse toResponse(Leitura leitura){
        if (leitura==null){
            return null;
        }
        return new LeituraResponse(
                leitura.getUuid(),
                leitura.isDeteccaoSimNao(),
                leitura.getDeteccaoTipo(),
                leitura.getHorarioDetec(),
                leitura.getLocal(),
                leitura.getSensor(),
                leitura.getValorAferido(),
                leitura.getEventos(),
                leitura.getCreatedBy(),
                leitura.getLastModifiedBy(),
                leitura.getCreatedAt(),
                leitura.getUpdatedAt()
        );
    }
}
