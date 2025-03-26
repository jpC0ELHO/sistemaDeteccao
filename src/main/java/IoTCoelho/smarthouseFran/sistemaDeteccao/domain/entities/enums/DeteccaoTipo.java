package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums;

public enum DeteccaoTipo {

    OBJETO("Objeto"),
    OBJETO_MOVIMENTO("Objeto em Movimento"),
    ANIMAL("Animal"),
    GAS("GAS"),
    CO2("CO2"),
    GLP("GLP"),
    ANIMAL_MOVIMENTO("Animal em Movimento"),
    CALOR_ALTO("Calor Alto"),
    HIPERCALOR("Hipercalor");

    private final String descricao;

    DeteccaoTipo(String descricao){
        this.descricao=descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    public static DeteccaoTipo fromDescricao(String descricao) {
        for (DeteccaoTipo tipo : DeteccaoTipo.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de deteccao invalido: " + descricao);
    }
}