package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.evento;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.EventoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.EventoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.EventoTipo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventoService {
    List<EventoResponse>findEventoList();
    Optional<EventoResponse>findEventoId(UUID uuid);
    void createEvento(EventoRequest eventoRequest);
    EventoResponse detecEventoLeitura(Leitura leitura, EventoTipo eventoTipo);
    void updateEvento(UUID uuid,EventoRequest eventoRequest);
    void deleteEvento(UUID uuid);
}
