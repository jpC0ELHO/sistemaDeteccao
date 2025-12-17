package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums;

public enum Role {
    ADMIN("ADM"),
    USUARIO("USUARIO"),
    MANUTENCAO("MANUTENCAO"),
    VISITANTE("VISITANTE");
    private final String name;
    Role(String name){
        this.name=name;
    }
}
