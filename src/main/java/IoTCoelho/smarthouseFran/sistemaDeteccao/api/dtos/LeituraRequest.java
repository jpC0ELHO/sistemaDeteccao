package IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Local;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.DeteccaoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record LeituraRequest(
        @NotNull
        boolean deteccao_sim_nao,
        @NotNull
        DeteccaoTipo deteccaoTipo,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @JsonSerialize(using = DateSerializer.class)
        @NotNull
        LocalDateTime horarioDetec,
        @NotNull
        Local local,
        @NotNull
        Sensores sensores
) {
    public static Leitura toEntidade(LeituraRequest leituraRequest){
        if (leituraRequest==null){
            return null;
        }
        return new Leitura(
                leituraRequest.deteccao_sim_nao,
                leituraRequest.deteccaoTipo,
                leituraRequest.horarioDetec,
                leituraRequest.local,
                leituraRequest.sensores
        );
    }
}
