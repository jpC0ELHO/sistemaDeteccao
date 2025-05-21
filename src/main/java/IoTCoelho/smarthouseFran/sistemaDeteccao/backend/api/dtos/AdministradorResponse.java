package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Administrador;
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
@JsonPropertyOrder({"uuid","createdBy","lastModifiedBy","createdAt","updatedAt","username","password","role","cpf",
        "ativo","isAccountNonExpired","isAccountNonLocked","isCredentialNonExpired","isEnabled"})
public record AdministradorResponse(
        UUID uuid,
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
        LocalDateTime updateAt,
        String username,
        String password,
        Role role,
        String cpf,
        Boolean ativo


) {
    public static AdministradorResponse toResponse(Administrador administrador){
        if (administrador==null){
            return null;
        }
        return new AdministradorResponse(
                administrador.getUuid(),
                administrador.getCreatedBy(),
                administrador.getLastModifiedBy(),
                administrador.getCreatedAt(),
                administrador.getUpdatedAt(),
                administrador.getUsername(),
                administrador.getPassword(),
                administrador.getRole(),
                administrador.getCpf(),
                administrador.isAtivo()

        );
    }
}
