package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dispositivos")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Device extends Entidade{
    @Column(name = "nome_do_dispositivo")
    private String nomeDispositivo;
    @Column(name = "tipo_dispositivo")
    private String tipoDispositivo;
    @Column(name = "ip_permitido")
    private String ipPermitido;
    @Column(nullable = false)
    private  Boolean status;
    @Column(name = "ultimo_acesso",nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime ultimoAcesso;
    @Column(unique = true,name = "chave_de_acesso",nullable = false)
    private String chaveDeAcesso;
}
