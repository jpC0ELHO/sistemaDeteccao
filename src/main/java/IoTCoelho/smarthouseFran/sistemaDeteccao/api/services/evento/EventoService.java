package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.evento;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Evento;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.EventoTipo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventoService {
    List<EventoResponse>findEventoList();
    Optional<EventoResponse>findEventoId(UUID uuid);
    void createEvento(EventoRequest eventoRequest);
    Evento createEventoLeitura(Leitura leitura, EventoTipo eventoTipo);
    void updateEvento(UUID uuid,EventoRequest eventoRequest);
    void deleteEvento(UUID uuid);
}
