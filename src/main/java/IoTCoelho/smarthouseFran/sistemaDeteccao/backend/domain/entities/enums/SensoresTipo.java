package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums;

import lombok.Getter;

public enum SensoresTipo {
    CO2("CO2"),
    FUMACA("FUMACA"),
    CHAMAS("CHAMAS"),
    GAS("GAS"),
    LIQUIDO("LIQUIDO"),
    CALOR("CALOR"),
    MOVIMENTO("MOVIMENTO"),
    SEGURANCA("SEGURANCA");

    @Getter
    private final String descricao;

    SensoresTipo(String descricao){
        this.descricao=descricao;
    }
}
