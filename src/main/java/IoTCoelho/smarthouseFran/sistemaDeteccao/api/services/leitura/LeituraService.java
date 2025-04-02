package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.leitura;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LeituraRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.LeituraResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeituraService {
    List<LeituraResponse>findLeituraList();
    Optional<LeituraResponse>findLeituraId(UUID uuid);
    void processarLeitura(LeituraRequest leituraRequest);
    void createLeitura(LeituraRequest leituraRequest);
    void updateLeitura(UUID uuid,LeituraRequest leituraRequest);
    void deleteLeitura(UUID uuid);

}
