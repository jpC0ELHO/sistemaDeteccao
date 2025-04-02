package IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.EventoTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeteccaoRequest {
    private Leitura leitura;
    private EventoTipo eventoTipo;
}
