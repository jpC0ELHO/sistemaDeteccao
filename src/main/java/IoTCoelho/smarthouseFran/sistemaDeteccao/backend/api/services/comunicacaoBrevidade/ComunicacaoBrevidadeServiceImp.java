package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.comunicacaoBrevidade;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.comunicacaoBrevidade.ComunicacaoBrevidadeRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.comunicacaoBrevidade.ComunicacaoBrevidadeResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories.ComunicacaoBrevidadeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.comunicacaoBrevidade.ComunicacaoBrevidadeRequest.toEntidade;

@Service
@AllArgsConstructor
@Log4j2
public class ComunicacaoBrevidadeServiceImp implements ComunicacaoBrevidadeService{

    private final ComunicacaoBrevidadeRepository comunicacaoBrevidadeRepository;

    @Override
    public List<ComunicacaoBrevidadeResponse> findComunicacaoList() {
        try {
            var findComunicacaoBrevidaList=comunicacaoBrevidadeRepository.findAll();
            if (findComunicacaoBrevidaList==null){
                throw new ModelNotFoundException("Lista de comunicacoes nao encontrada!");
            }
            return findComunicacaoBrevidaList.stream().map(ComunicacaoBrevidadeResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Erro no laco de lista: ",e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<ComunicacaoBrevidadeResponse> findComunicacaoId(UUID uuid) {
        return comunicacaoBrevidadeRepository
                .findById(uuid)
                .map(ComunicacaoBrevidadeResponse::toResponse)
                .or(()->{
            throw new ModelNotFoundException("ID nao"+uuid+" encontrado!");
        });

    }

    @Override
    public void createComunicacaoBrevidade(ComunicacaoBrevidadeRequest comunicacaoBrevidadeRequest) {
        comunicacaoBrevidadeRepository.save(toEntidade(comunicacaoBrevidadeRequest));
    }

    @Override
    public void updateComunicacaoBrevidade(UUID uuid, ComunicacaoBrevidadeRequest comunicacaoBrevidadeRequest) {
    var findComunicacaoBrevidadeId=comunicacaoBrevidadeRepository
            .findById(uuid)
            .orElseThrow(()-> new ModelNotFoundException("ID: "+uuid+" nao encontrado"));

    findComunicacaoBrevidadeId.setMacAddress(comunicacaoBrevidadeRequest.macAddress());
    findComunicacaoBrevidadeId.setLatitudeAtual(comunicacaoBrevidadeRequest.latitudeAtual());
    findComunicacaoBrevidadeId.setLongitudeAtual(comunicacaoBrevidadeRequest.longitudeAtual());
    findComunicacaoBrevidadeId.setLatitudeReferencia(comunicacaoBrevidadeRequest.latitudeReferencia());
    findComunicacaoBrevidadeId.setLongitudeReferencia(comunicacaoBrevidadeRequest.longitudeReferencia());
    findComunicacaoBrevidadeId.setDistanciaCalculadaKm(comunicacaoBrevidadeRequest.distanciaCalculadaKm());
    findComunicacaoBrevidadeId.setForaDoPerimetro(comunicacaoBrevidadeRequest.foraDoPerimetro());
    findComunicacaoBrevidadeId.setBrevidadeConfirmada(comunicacaoBrevidadeRequest.brevidadeConfirmada());
    findComunicacaoBrevidadeId.setEnviadoAoSmartwatch(comunicacaoBrevidadeRequest.enviadoAoSmartwatch());
    findComunicacaoBrevidadeId.setDataEnvio(comunicacaoBrevidadeRequest.dataEnvio());
    comunicacaoBrevidadeRepository.save(findComunicacaoBrevidadeId);
    }

    @Override
    public void deleteComunicacaoBrevidade(UUID uuid) {
        try {
            var findComunicacaoBrevidadeId=comunicacaoBrevidadeRepository
                    .findById(uuid)
                    .orElseThrow(()-> new ModelNotFoundException("ID:"+uuid+" para deletar nao encontrado!"));
            comunicacaoBrevidadeRepository.delete(findComunicacaoBrevidadeId);
        }catch (RuntimeException e){
            log.error("Error: {} apos tentar deletar comunicacao brevidade!",e.getMessage());
            throw e;
        }
    }
}
