package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.communication.ComunicacaoBrevidade;

import java.time.LocalDateTime;

public record ComunicacaoBrevidadeRequest(
        String macAddress,

        Double latitudeAtual,
        Double longitudeAtual,

        Double latitudeReferencia,
        Double longitudeReferencia,

        Double distanciaCalculadaKm,

        Boolean foraDoPerimetro,
        Boolean brevidadeConfirmada,

        Boolean enviadoAoSmartwatch,
        LocalDateTime dataEnvio
){
    public static ComunicacaoBrevidade toEntidade(ComunicacaoBrevidadeRequest comunicacaoBrevidadeRequest){
        if (comunicacaoBrevidadeRequest==null){
            return null;
        }
        return new ComunicacaoBrevidade(
                comunicacaoBrevidadeRequest.macAddress(),
                comunicacaoBrevidadeRequest.latitudeAtual(),
                comunicacaoBrevidadeRequest.longitudeAtual(),
                comunicacaoBrevidadeRequest.latitudeReferencia(),
                comunicacaoBrevidadeRequest.longitudeReferencia(),
                comunicacaoBrevidadeRequest.distanciaCalculadaKm(),
                comunicacaoBrevidadeRequest.foraDoPerimetro(),
                comunicacaoBrevidadeRequest.brevidadeConfirmada(),
                comunicacaoBrevidadeRequest.enviadoAoSmartwatch(),
                comunicacaoBrevidadeRequest.dataEnvio()
        );
    }
}
