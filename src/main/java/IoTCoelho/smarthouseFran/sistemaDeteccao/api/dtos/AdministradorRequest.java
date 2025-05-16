package IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Administrador;
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
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AdministradorRequest(
        @NotNull
        UUID uuid,
        @NotBlank
        String createdBy,
        @NotNull
        String lastModifiedBy,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @JsonSerialize(using = DateSerializer.class)
        @NotBlank
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
        @JsonSerialize(using = DateSerializer.class)
        @NotNull
        LocalDateTime updateAt,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull
        Role role,
        @NotBlank
        String cpf,
        @NotNull
        boolean isAtivo

) {
    public static Administrador toEntidade(AdministradorRequest administradorRequest){
        if (administradorRequest==null){
            return null;
        }
        return new Administrador(
                administradorRequest.uuid,
                administradorRequest.createdBy,
                administradorRequest.lastModifiedBy,
                administradorRequest.createdAt,
                administradorRequest.updateAt,
                administradorRequest.username,
                administradorRequest.password,
                administradorRequest.role,
                administradorRequest.cpf,
                administradorRequest.isAtivo
        );
    }
}
