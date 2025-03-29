package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.localizacao;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LocalizacaoRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LocalizacaoResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums.Regiao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface LocalizacaoService {
    List<LocalizacaoResponse>findLocalizacaoList();
    Optional<LocalizacaoResponse> findLocalizacaoId(UUID uuid);
    void createLocalizacao(LocalizacaoRequest localizacaoRequest);
    void updateLocalizacao(UUID uuid,LocalizacaoRequest localizacaoRequest);
    void deleteLocalizacao(UUID uuid);
    void atualizarRegiao(UUID uuid, Map<Regiao,Boolean>novasRegioes);
}
