package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.exceptions;


public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message){
        super(message);
    }
}