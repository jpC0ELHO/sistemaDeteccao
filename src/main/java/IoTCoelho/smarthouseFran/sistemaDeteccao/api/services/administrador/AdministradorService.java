package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.administrador;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.AdministradorRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.AdministradorResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdministradorService {
    List<AdministradorResponse>findAdministradorList();
    Optional<AdministradorResponse> findAdministradorId(UUID uuid);
    void createAdministrador(AdministradorRequest administradorRequest);
    void updateAdministrador(UUID uuid, AdministradorRequest administradorRequest);
    void deleteAdministrador(UUID uuid);
}
