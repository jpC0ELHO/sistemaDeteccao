package IoTCoelho.smarthouseFran.sistemaDeteccao.api.services.administrador;

import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.AdministradorRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.AdministradorResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelIntegrityViolationException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.repositories.AdministradorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.api.dtos.AdministradorRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class AdministradorServiceImp implements AdministradorService {
    private final AdministradorRepository administradorRepository;
    @Override
    public List<AdministradorResponse> findAdministradorList() {
        try {
            var findAdministradorList=administradorRepository.findAll();
            if (findAdministradorList.isEmpty()){
                log.info("List not found!");
                throw new ModelNotFoundException("List not found!");
            }
            return findAdministradorList.stream().map(AdministradorResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.info("Error :{} ",e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<AdministradorResponse> findAdministradorId(UUID uuid) {
        return administradorRepository.findById(uuid).map(AdministradorResponse::toResponse).or(
                ()->{
                 log.error("Id: {} not found!",uuid);
                 throw new ModelNotFoundException("Id "+uuid+" not found!");
                }
        );
    }

    @Override
    public void createAdministrador(AdministradorRequest administradorRequest) {
    var findAdministradorCpf=administradorRepository.findByCpf(administradorRequest.cpf());
    if (findAdministradorCpf.isPresent()){
    log.error("CPF: {} already exists!",administradorRequest.cpf());
    throw new ModelIntegrityViolationException("CPF already exits!");
    }
    administradorRepository.save(toEntidade(administradorRequest));
    }

    @Override
    public void updateAdministrador(UUID uuid, AdministradorRequest administradorRequest) {
    var findAdministradorId=administradorRepository
            .findById(uuid)
            .orElseThrow(()-> new ModelNotFoundException("ID:"+uuid+" not found!"));
    boolean isAdmin=findAdministradorId.getAuthorities().stream().anyMatch(auth->auth.getAuthority().equals("SUPER_ADMIN"));
    if (!isAdmin){
        log.error("You don't have the required authority to perform this operation.");
        //throw new AccessDeniedException("Insufficient authority");
    }
        findAdministradorId.setUsername(administradorRequest.username());
        findAdministradorId.setPassword(administradorRequest.password());
        findAdministradorId.setCpf(administradorRequest.cpf());
        findAdministradorId.setAtivo(administradorRequest.isAtivo());
    }

    @Override
    public void deleteAdministrador(UUID uuid) {
    try {
        var findAdminId=administradorRepository.findById(uuid)
                .orElseThrow(()-> new ModelNotFoundException("ID: "+uuid+"not found"));
        administradorRepository.delete(findAdminId);
        log.info("Admin with:{} deleted!",uuid);
    }catch (RuntimeException e){
        log.error("Error :",e.getMessage());
    }
    }
}
