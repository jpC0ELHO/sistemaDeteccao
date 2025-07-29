package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.exceptions;

import java.sql.SQLTimeoutException;

public class TimeOutSql extends SQLTimeoutException {
    public TimeOutSql(String message){
        super("Tempo de conexão excedido: "+message);
    }
}
