package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums;

import java.util.Arrays;
import java.util.Optional;

public enum EventoTipo {
    //brevidades
    INCENDIO("INCENDIO",180, Integer.MAX_VALUE),
    COMBUSTAO("COMBUSTAO",200,999),
    VAZAMENTO_GAS("VAZAMENTO GAS",50,Integer.MAX_VALUE),
    //Atipicos
    FALHA("FALHA",-100,0),
    LIQUIDO("LIQUIDO",1,100),
    AMEACA("AMEACA",1,1),
    //sensor de calor
    PRESENCA("PRESENCA",1,1),
    //cameras de seguranca
    SUSPEITO("SUSPEITO",1,1),
    CONHECIDO("CONEHCIDO",1,1);

    private final String descricao;
    private final int valorMin;
    private final int valorMax;
    EventoTipo(String descricao,int valorMin,int valorMax){
        this.descricao=descricao;
        this.valorMax=valorMax;
        this.valorMin=valorMin;
    }
    public static Optional<EventoTipo> detectarEvento(int valorSensor) {
        return Arrays.stream(values())
                .filter(evento -> valorSensor >= evento.valorMin && valorSensor <= evento.valorMax)
                .findFirst();
    }
}
