package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.comunicacaoBrevidade;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.comunicacaoBrevidade.ComunicacaoBrevidadeRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.comunicacaoBrevidade.ComunicacaoBrevidadeResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComunicacaoBrevidadeService {
    List<ComunicacaoBrevidadeResponse>findComunicacaoList();
    Optional<ComunicacaoBrevidadeResponse>findComunicacaoId(UUID uuid);
    void createComunicacaoBrevidade(ComunicacaoBrevidadeRequest comunicacaoBrevidadeRequest);
    void updateComunicacaoBrevidade(UUID uuid,ComunicacaoBrevidadeRequest comunicacaoBrevidadeRequest);
    void deleteComunicacaoBrevidade(UUID uuid);
}
