package IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Usuario;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UsuarioRequest(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String email,
        @NotNull
        Role role,
        @NotNull
        Boolean ativo,
        @JsonSerialize(using = DateSerializer.class)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @NotNull
        LocalDateTime dataNasci
) {
    public static Usuario toEntidade(UsuarioRequest usuarioRequest){
        if (usuarioRequest==null){
            return null;
        }
        return new Usuario(
                usuarioRequest.username,
                usuarioRequest.password,
                usuarioRequest.email,
                usuarioRequest.role,
                usuarioRequest.ativo,
                usuarioRequest.dataNasci
        );
    }
}
