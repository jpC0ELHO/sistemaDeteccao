package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.communication;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Entidade;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.devices.Device;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.DeviceMensage;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_session")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@EntityListeners(AuditingEntityListener.class)
public class DeviceSession extends Entidade {
    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id")
    private Device device;
    @Column(name = "tempo")
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime tempo;
    @Enumerated(EnumType.STRING)
    private DeviceMensage deviceMenssage;
}
