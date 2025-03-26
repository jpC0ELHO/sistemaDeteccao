package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums;

public enum EventoTipo {
    //brevidades
    INCENDIO("INCENDIO"),
    COMBUSTAO("COMBUSTAO"),
    VAZAMENTO_GAS("VAZAMENTO GAS"),
    //Atipicos
    FALHA("FALHA"),
    LIQUIDO("LIQUIDO"),
    AMEACA("AMEACA"),
    //sensor de calor
    PRESENCA("PRESENCA"),
    //cameras de seguranca
    SUSPEITO("SUSPEITO"),
    CONHECIDO("CONEHCIDO");

    private final String descricao;

    EventoTipo(String descricao){
        this.descricao=descricao;
    }
}
