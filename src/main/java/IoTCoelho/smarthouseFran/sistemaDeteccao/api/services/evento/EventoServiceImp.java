package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.evento;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories.EventoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.EventoRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class EventoServiceImp implements EventoService{
    private final EventoRepository eventoRepository;
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
        var findEventoId=eventoRepository.findById(uuid);
        if (findEventoId.isEmpty()){
            throw new ModelNotFoundException("ID: "+uuid+" not found!");
        }
        return findEventoId.map(EventoResponse::toResponse);
    }
    //Todos os eventos devem ser salvos, por esse motivo não há tratamento na classe, apenas de erros.
    @Override
    public void createEvento(EventoRequest eventoRequest) {
    eventoRepository.save(toEntidade(eventoRequest));
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
