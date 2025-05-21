package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Usuario;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.Role;
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
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","usarname","password","email","role","ativo","createdBy","lastModifiedBy","createdAt","updatedAt"})
public record UsuarioResponse(
        UUID uuid,
        String username,
        String password,
        String email,
        Role role,
        Boolean ativo,
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
        LocalDateTime updateAt

) {
    public static UsuarioResponse toResponse(Usuario usuario){
        if (usuario==null){
            return null;
        }
        return new UsuarioResponse(
                usuario.getUuid(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getAtivo(),
                usuario.getCreatedBy(),
                usuario.getLastModifiedBy(),
                usuario.getCreatedAt(),
                usuario.getUpdatedAt()

        );
    }
}
