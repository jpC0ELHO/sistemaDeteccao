package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Localizacao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Regiao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LocalizacaoRequest(
        @NotNull
        Sensores sensores,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude,
        @NotNull
        Map<Regiao,Boolean> regiao,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        @NotNull
        LocalDateTime dataInstalacao
){
    public static Localizacao toEntidade(LocalizacaoRequest localizacaoRequest){
        if (localizacaoRequest==null){
            return null;
        }
        return new Localizacao(
                localizacaoRequest.sensores,
                localizacaoRequest.latitude,
                localizacaoRequest.longitude,
                localizacaoRequest.regiao,
                localizacaoRequest.dataInstalacao
        );
    }
}
