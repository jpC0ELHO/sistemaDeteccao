package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions;


public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message){
        super(message);
    }
}