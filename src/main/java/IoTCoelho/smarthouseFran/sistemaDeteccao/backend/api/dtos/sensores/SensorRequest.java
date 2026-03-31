package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.sensores;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.devices.Sensor;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.SensoresTipo;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SensorRequest(
        @NotBlank
        String nome,
        @NotNull
        boolean ativadoDesativado,
        @NotNull
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime horarioAcionamento,
        @NotNull
        BigDecimal memoriaUsada,
        @NotNull
        BigDecimal memoriaDisponivel,
        @NotNull
        BigDecimal valorDadosTransferencia,
        @NotBlank
        String dadosDescricao,
        @NotNull
        Map<Regiao,Boolean> regiao,
        @NotNull
        Map<SensoresTipo,Boolean>sensoresTipo
) {
    public static Sensor toEntidade(SensorRequest sensorRequest){
        if (sensorRequest ==null){
            return null;
        }
        return new Sensor(
                sensorRequest.nome,
                sensorRequest.ativadoDesativado,
                sensorRequest.horarioAcionamento,
                sensorRequest.memoriaUsada,
                sensorRequest.memoriaDisponivel,
                sensorRequest.valorDadosTransferencia,
                sensorRequest.dadosDescricao,
                sensorRequest.regiao,
                sensorRequest.sensoresTipo
        );
    }
}
