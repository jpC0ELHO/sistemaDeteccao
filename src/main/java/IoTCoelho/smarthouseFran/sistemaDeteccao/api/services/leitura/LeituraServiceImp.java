package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.leitura;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LeituraRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LeituraResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories.LeituraRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LeituraRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class LeituraServiceImp implements LeituraService {
        private final LeituraRepository leituraRepository;
    @Override
    public List<LeituraResponse> findLeituraList() {
        try {
            var findLeituraList=leituraRepository.findAll();
            if (findLeituraList.isEmpty()){
                throw new ModelNotFoundException("List not found!");
            }
            return findLeituraList.stream().map(LeituraResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error: {}",e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<LeituraResponse> findLeituraId(UUID uuid) {
        var findLeituraId=leituraRepository.findById(uuid);
        if (findLeituraId.isEmpty()){
            log.error("ID: {} not found!",uuid);
            throw new ModelNotFoundException("Id: "+uuid+" not found!");
        }
        return findLeituraId.map(LeituraResponse::toResponse);
    }

    @Override
    public void createLeitura(LeituraRequest leituraRequest) {
        //Armazenar qualquer tipo de leitura, por isso não há tratamento, isso será gerado de forma automatica
       leituraRepository.save(toEntidade(leituraRequest));
    }

    @Override
    public void updateLeitura(UUID uuid, LeituraRequest leituraRequest) {
    var findLeituraId=leituraRepository
            .findById(uuid)
            .orElseThrow(()-> new ModelNotFoundException("ID: "+uuid+" not found!"));
    findLeituraId.setDeteccao_sim_nao(leituraRequest.deteccao_sim_nao());
    findLeituraId.setDeteccaoTipo(leituraRequest.deteccaoTipo());
    findLeituraId.setSensor(leituraRequest.sensores());
    findLeituraId.setLocal(leituraRequest.local());
    leituraRepository.save(findLeituraId);
    }

    @Override
    public void deleteLeitura(UUID uuid) {
    try {
        var findLeituraId=leituraRepository
                .findById(uuid)
                .orElseThrow(()-> new ModelNotFoundException("ID : "+uuid+" not found!"));
    }catch (RuntimeException e){
        log.error("Erro:{}",e.getMessage());
        throw e;
    }
    }
}
