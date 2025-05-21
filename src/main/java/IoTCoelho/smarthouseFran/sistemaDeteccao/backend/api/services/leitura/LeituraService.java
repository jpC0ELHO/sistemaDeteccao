package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.leitura;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.LeituraRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.LeituraResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Leitura;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeituraService {
    void saveLeitura(Leitura leitura);
    List<LeituraResponse>findLeituraList();
    Optional<LeituraResponse>findLeituraId(UUID uuid);
    void processarLeitura(LeituraRequest leituraRequest);
    void createLeitura(LeituraRequest leituraRequest);
    void updateLeitura(UUID uuid,LeituraRequest leituraRequest);
    void deleteLeitura(UUID uuid);
}
