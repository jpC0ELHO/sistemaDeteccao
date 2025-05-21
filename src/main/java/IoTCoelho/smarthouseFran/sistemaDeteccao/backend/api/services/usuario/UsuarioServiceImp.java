package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.usuario;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.UsuarioRequest;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.UsuarioResponse;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.exceptions.ModelIntegrityViolationException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.exceptions.ModelNotFoundException;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.UsuarioRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class UsuarioServiceImp implements UsuarioService{
    private final UsuarioRepository usuarioRepository;
    @Override
    public List<UsuarioResponse> findUsuarioList() {
        try {
            var findUsuarioList=usuarioRepository.findAll();
            if (findUsuarioList.isEmpty()){
                log.info("List not found!!");
                throw new ModelNotFoundException("List not found!");
            }
            return findUsuarioList.stream().map(UsuarioResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error :{} ",e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<UsuarioResponse> findUsuarioId(UUID uuid) {
        var findUsuarioId=usuarioRepository.findById(uuid);
        if (findUsuarioId.isEmpty()){
            throw new ModelNotFoundException("ID : "+uuid+" not found!");
        }
        return findUsuarioId.map(UsuarioResponse::toResponse);
    }

    @Override
    public void createUsuario(UsuarioRequest usuarioRequest) {
    var findUsuarioName=usuarioRepository.findByName(usuarioRequest.username());
    if (findUsuarioName.isPresent()){
        log.info("Usernamen: {} already exists!",usuarioRequest.username());
        throw new ModelIntegrityViolationException("Username already exists!");
    }
    usuarioRepository.save(toEntidade(usuarioRequest));
    }

    @Override
    public void updateUsuario(UUID uuid, UsuarioRequest usuarioRequest) {
    var findUsuarioId=usuarioRepository.findById(uuid);
    if (findUsuarioId.isEmpty()){
        log.info("Id not found!");
        throw new ModelNotFoundException("ID: "+uuid+" not found, try again!");
    }
    findUsuarioId.map(usuario -> {
        usuario.setUsername(usuarioRequest.username());
        usuario.setPassword(usuarioRequest.password());
        usuario.setDataNasci(usuarioRequest.dataNasci());
        usuario.setEmail(usuarioRequest.email());
        return usuarioRepository.save(usuario);
    });
    }

    @Override
    public void deleteUsuario(UUID uuid) {
    try {
        var findUsuarioId=usuarioRepository
                .findById(uuid)
                .orElseThrow(()-> new ModelNotFoundException("Id: "+uuid+" not found!"));
        usuarioRepository.delete(findUsuarioId);
    }catch (RuntimeException e){
        log.error(e.getMessage());
    }
    }
}
