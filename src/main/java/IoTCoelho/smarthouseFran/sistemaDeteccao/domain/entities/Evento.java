package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.EventoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Entity
@Data
@Table(name = "evento")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Evento extends Entidade {
    @Column(nullable = false)
    private String tipoEvento;
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
    @Column(nullable = false)
    private String descricao;
    @Column(name = "horario_evento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horarioEvento;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "evento_eventoTipo", joinColumns = @JoinColumn(name = "evento_id"))
    @MapKeyEnumerated(EnumType.STRING) // Armazena a chave como texto no banco
    @MapKeyColumn(name = "evento_tipo") // Nome da chave na tabela
    @Column(name = "ativo") // Nome do valor (Boolean)
    private Map<EventoTipo,Boolean> eventoTipo = new HashMap<>();
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensores sensor;
}