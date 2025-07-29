package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.exceptions;

import java.sql.SQLException;

public class DatabaseSqlException extends SQLException {
    public DatabaseSqlException(String message){
        super("Erro no banco de dados detectado: "+message);
    }
}
