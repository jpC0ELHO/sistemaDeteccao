package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums;

public enum ProtocoloComunicacao {

    WI_FI("WI_FI"),
    LORA("LORA"),
    BLUETOOTH("BLUETOOTH"),
    MODBUS("MODBUS"),
    RS485("RS485"),
    MQTT("MQTT"),
    ZIGBEE("ZIGBEE"),
    NB_IOT("NB_IOT");

    private final String descicao;

    ProtocoloComunicacao(String descicao){
        this.descicao=descicao;
    }

    public String getProtocoloComunicacao(){
        return descicao;
    }

    public static ProtocoloComunicacao fromDescricao(String descricao){
        for(ProtocoloComunicacao comunicacao:ProtocoloComunicacao.values()){
            if (comunicacao.getProtocoloComunicacao().equalsIgnoreCase(descricao)){
                return comunicacao;
            }
        }
        throw new IllegalArgumentException("Tipo de comunicação invalido: "+descricao);
    }
}