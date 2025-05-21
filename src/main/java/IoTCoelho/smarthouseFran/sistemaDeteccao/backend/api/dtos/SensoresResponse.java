package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.SensoresTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","nome","ativadoDesativado","horarioAcionamento","memoriaUsada","memoriaDisponivel",
"valorDadosTransferencia","dadosDescricao","regiao","sensorTipo","createdBy","lastModifiedBy","createdAt","updatedAt"})
public record SensoresResponse(
        UUID uuid,
        String nome,
        boolean ativadoDesativado,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime horarioAcionamento,
        BigDecimal memoriaUsada,
        BigDecimal memoriaDisponivel,
        BigDecimal valorDadosTransferencia,
        String dadosDescricao,
        Map<Regiao,Boolean>regiao,
        Map<SensoresTipo,Boolean>sensoresTipo,
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
    public static SensoresResponse toResponse(Sensores sensores){
        if (sensores==null){
            return null;
        }
        return new SensoresResponse(
          sensores.getUuid(),
          sensores.getNome(),
          sensores.isAtivadoDesativado(),
          sensores.getHorarioAcionamento(),
          sensores.getMemoriaUsada(),
          sensores.getMemoriaDisponivel(),
          sensores.getValorDadosTransferencia(),
          sensores.getDadosDescricao(),
          sensores.getRegiao(),
          sensores.getSensorTipo(),
          sensores.getCreatedBy(),
          sensores.getLastModifiedBy(),
          sensores.getCreatedAt(),
          sensores.getUpdatedAt()
        );
    }
}
