package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.SensoresTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sensores")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Sensores extends Entidade{
    @Column(nullable = false,name = "nome",length = 15)
    private String nome;
    @Column(name = "status")
    private boolean ativadoDesativado;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horarioAcionamento;
    @Column(name = "memoria_usada", precision = 15, scale = 2)
    private BigDecimal memoriaUsada;
    @Column(name = "memoria_disponivel", precision = 15, scale = 2)
    private BigDecimal memoriaDisponivel;
    @Column(name = "valor_dados_transferencia", precision = 15, scale = 2)
    private BigDecimal valorDadosTransferencia;
    @Column(name = "descricao_dados")
    private String dadosDescricao;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "sensor_regioes", joinColumns = @JoinColumn(name = "sensor_id"))
    @MapKeyEnumerated(EnumType.STRING) // Armazena a chave como texto no banco
    @MapKeyColumn(name = "regiao") // Nome da chave na tabela
    @Column(name = "ativo") // Nome do valor (Boolean)
    private Map<Regiao, Boolean> regiao = new HashMap<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "sensor_tipos", joinColumns = @JoinColumn(name = "sensor_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "tipo")
    @Column(name = "ativo")
    private Map<SensoresTipo, Boolean> sensorTipo = new HashMap<>();

    public void atualizarSensores(Map<SensoresTipo, Boolean> novosEstados) {
        this.sensorTipo.putAll(novosEstados);
    }

    public void atualizarRegioes(Map<Regiao, Boolean> novasRegioes) {
        this.regiao.putAll(novasRegioes);
    }
}


