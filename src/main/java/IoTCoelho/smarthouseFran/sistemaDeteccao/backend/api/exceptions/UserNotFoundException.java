package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.exceptions;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Usuario;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Usuario usuario,String message){
        super("Usuario: "+usuario.getUsername()+" Not found error: "+message);
    }
}
