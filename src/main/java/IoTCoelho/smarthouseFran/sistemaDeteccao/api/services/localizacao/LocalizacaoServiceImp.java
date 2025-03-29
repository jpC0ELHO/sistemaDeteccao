package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.localizacao;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LocalizacaoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LocalizacaoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Localizacao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Regiao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelIntegrityViolationException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LocalizacaoRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class LocalizacaoServiceImp implements LocalizacaoService{

    private final LocalizacaoRepository localizacaoRepository;

    @Override
    public List<LocalizacaoResponse> findLocalizacaoList() {
        var findLocalizacaoList = localizacaoRepository.findAll();
        if (findLocalizacaoList.isEmpty()) {
            log.error("List not found!");
            throw new ModelNotFoundException("List not found!");
        }
        return findLocalizacaoList.stream().map(LocalizacaoResponse::toResponse).toList();
    }

    @Override
    public Optional<LocalizacaoResponse> findLocalizacaoId(UUID uuid) {
        var findLocalizacaoId=localizacaoRepository
                .findById(uuid);
        if (findLocalizacaoId.isEmpty()){
            throw new ModelNotFoundException("ID: "+uuid+" not found!");
        }
        return findLocalizacaoId.map(LocalizacaoResponse::toResponse);
    }

    @Override
    public void createLocalizacao(LocalizacaoRequest localizacaoRequest) {
        var findLocalizacaoByRegiao=localizacaoRepository.findByRegiao(localizacaoRequest.regiao());
        if (findLocalizacaoByRegiao.isPresent()){
            throw new ModelIntegrityViolationException("Localizacao with Regiao: "
                    +localizacaoRequest.regiao()+" already exists!");
        }
        localizacaoRepository.save(toEntidade(localizacaoRequest));
    }

    @Override
    public void updateLocalizacao(UUID uuid, LocalizacaoRequest localizacaoRequest) {
        var findLocalizacaoId=localizacaoRepository
                .findById(uuid)
                .orElseThrow(()-> new ModelNotFoundException("Localizacao ID: "+uuid+" not found!"));
        if (localizacaoRequest.sensores()!=null){
            findLocalizacaoId.setSensor(localizacaoRequest.sensores());
        }
        else if (localizacaoRequest.latitude()!=null){
            findLocalizacaoId.setLatitude(localizacaoRequest.latitude());
        }else if (localizacaoRequest.longitude()!=null){
            findLocalizacaoId.setLongitude(localizacaoRequest.longitude());
        }
        localizacaoRepository.save(findLocalizacaoId);
    }

    @Override
    public void deleteLocalizacao(UUID uuid) {
    try{
        var findLocalizacaoId=localizacaoRepository
                .findById(uuid)
                .orElseThrow(()-> new ModelNotFoundException("Localizacao ID: "+uuid+" not found!"));
        localizacaoRepository.delete(findLocalizacaoId);
    }catch (RuntimeException e){
        log.error("Error: {}",e.getMessage());
        throw e;
    }
    }

    @Override
    public void atualizarRegiao(UUID uuid, Map<Regiao, Boolean> novasRegioes) {
        Localizacao localizacao=localizacaoRepository.findById(uuid)
                .orElseThrow(() -> new ModelNotFoundException("Localizacao com ID " + uuid + " não encontrado"));

        if (localizacao.getRegiao() == null) {
            localizacao.setRegiao(new HashMap<>());
        }
        novasRegioes.forEach(localizacao.getRegiao()::put);
        localizacaoRepository.save(localizacao);
        log.info("Regiões do sensor {} atualizadas com sucesso.", uuid);
    }
}
