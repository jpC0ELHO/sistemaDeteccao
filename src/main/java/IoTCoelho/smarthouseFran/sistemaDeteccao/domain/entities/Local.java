package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Regiao;
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
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local")
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Local extends Entidade{
    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensores sensor;
    @Column(precision = 5,name = "Latitude",nullable = false)
    private Double latitude;
    @Column(precision = 5,name = "Longitude",nullable = false)
    private Double longitude;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "sensor_regioes", joinColumns = @JoinColumn(name = "sensor_id"))
    @MapKeyEnumerated(EnumType.STRING) // Armazena a chave como texto no banco
    @MapKeyColumn(name = "regiao") // Nome da chave na tabela
    @Column(name = "ativo") // Nome do valor (Boolean)
    private Map<Regiao, Boolean> regiao = new HashMap<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInstalacao;
}