package IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.enums;

public enum Regiao {
    SALA("SALA"),
    QUARTO1("QUARTO1J"),
    QUARTO2("QUARTO2F"),
    ESCRITORIO("ESCRITORIO"),
    COZINHA("COZINHA"),
    BANHEIRO1("BANHEIRO1"),
    BANHEIRO2("BANHEIRO2F"),
    GARAGEM("GARAGEM"),
    CORREDOR_GARAGEM("CORREDOR GARAGEM"),
    AREA_DE_SERVICO("AREA DE SERVICO");
    private final String descricao;

    Regiao(String descricao){
        this.descricao=descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public static Regiao fromLocalidade(String descricao){
        for (Regiao local: Regiao.values()){
            if (local.getDescricao().equalsIgnoreCase(descricao)){
                return local;
            }
        }
        throw new IllegalArgumentException("Regiao invalida: "+descricao);
    }
}
