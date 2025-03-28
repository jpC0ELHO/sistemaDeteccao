package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class ModelIntegrityViolationException extends DataIntegrityViolationException {
    public ModelIntegrityViolationException(String msg) {
        super(msg);
    }

    public ModelIntegrityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

