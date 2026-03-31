package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.actions;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Entidade;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity(name = "deteccao")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Deteccao extends Entidade {
}
