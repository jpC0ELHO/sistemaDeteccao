package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums;

public enum Role {
    ADMIN("ADM"),
    SUPER_ADMIN("ADM GERAL"),
    USUARIO("USUARIO"),
    VISITANTE("VISITANTE"),
    MANUTENCAO("MANUTENCAO");
    private final String name;
    Role(String name){
        this.name=name;
    }
}
