package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.evento;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Evento;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.EventoTipo;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories.EventoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class EventoServiceImp implements EventoService{
    private final EventoRepository eventoRepository;
    private final SimpMessagingTemplate messagingTemplate;
    @Override
    public List<EventoResponse> findEventoList() {
        try {
            var findEventoList=eventoRepository.findAll();
            if (findEventoList.isEmpty()){
                throw new ModelNotFoundException("List not found!");
            }
            return findEventoList.stream().map(EventoResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error :{}",e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<EventoResponse> findEventoId(UUID uuid) {
        return eventoRepository.findById(uuid)
                .map(EventoResponse::toResponse)
                .or(() -> {
                    log.error("ID: {} not found!", uuid);
                    throw new ModelNotFoundException("ID: " + uuid + " not found!");
                });
    }
    //Todos os eventos devem ser salvos, por esse motivo não há tratamento na classe, apenas de erros.
    @Override
    @Transactional
    public void createEvento(EventoRequest eventoRequest) {
    Evento eventoSalvo=eventoRepository.save(toEntidade(eventoRequest));
    EventoResponse eventoResponse=EventoResponse.toResponse(eventoSalvo);
    messagingTemplate.convertAndSend("/topic/alertas",eventoResponse);
    log.info("Alert send: {}",eventoResponse);
    }

    @Override
    @Transactional
    public EventoResponse detecEventoLeitura(Leitura leitura, EventoTipo eventoTipo) {
        Evento evento = new Evento();
        evento.setSensor(leitura.getSensor());
        evento.setLocal(leitura.getLocal());
        evento.setHorarioEvento(LocalDateTime.now());
        evento.setDescricao("Evento detectado: " + eventoTipo.name());
        evento.setEventoTipo(Map.of(eventoTipo, true));

        Evento eventoSalvo = eventoRepository.save(evento);
        EventoResponse eventoResponse = EventoResponse.toResponse(eventoSalvo);
        messagingTemplate.convertAndSend("/topic/alertas", eventoResponse);

        log.info("Evento detectado enviado: {}", eventoResponse);
        return eventoResponse;
    }

    @Override
    public void updateEvento(UUID uuid, EventoRequest eventoRequest) {
        var findEventoId=eventoRepository
                .findById(uuid)
                .orElseThrow(()-> new ModelNotFoundException("Id : "+uuid+" not found!"));

        findEventoId.setLocal(eventoRequest.local());
        findEventoId.setDescricao(eventoRequest.descricao());
        findEventoId.setHorarioEvento(eventoRequest.horarioEvento());
        findEventoId.setEventoTipo(eventoRequest.eventoTipo());
        findEventoId.setSensor(eventoRequest.sensor());
        eventoRepository.save(findEventoId);
    }

    @Override
    public void deleteEvento(UUID uuid) {
        try {
            var findEventoId=eventoRepository
                    .findById(uuid)
                    .orElseThrow(()-> new ModelNotFoundException("ID: "+uuid+" not found!"));
            eventoRepository.delete(findEventoId);
        }catch (RuntimeException e){
            log.error("Error: {}",e.getMessage());
            throw e;
        }
    }
}
