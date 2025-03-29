package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.DeteccaoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
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

@Entity
@Data
@Table(name = "leitura")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Leitura extends Entidade{
    @Column(nullable = false, name = "deteccao_sim_nao")
    private boolean  deteccao_sim_nao;
    @Enumerated(EnumType.STRING)
    private DeteccaoTipo deteccaoTipo;
    @Column(name = "horario_detectado")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private LocalDateTime horarioDetec;
    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Localizacao local;
    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensores sensor;

    //Classe de atualizacao constante com WebSockets

}

