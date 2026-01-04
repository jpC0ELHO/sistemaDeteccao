package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comunicacao_brevidade")
public class ComunicacaoBrevidade extends Entidade{
    @Column(nullable = false)
    private String macAddress;//endereco mac
    // Localização atual do smartwatch
    @Column(name = "latitude_atual")
    private Double latitudeAtual;
    @Column(name = "longitude_atual")
    private Double longitudeAtual;
    // Localização fixa (ex: ponto de referência)
    @Column(name = "latitude_referencia")
    private Double latitudeReferencia;
    @Column(name = "longitude_referencia")
    private Double longitudeReferencia;
    // Resultado do cálculo de distância
    @Column(name = "distancia_calculada_km")
    private Double distanciaCalculadaKm;
    // Flags de validação
    @Column(name = "fora_da_area")
    private Boolean foraDoPerimetro;
    @Column(name = "brevidade_confirmada")
    private Boolean brevidadeConfirmada;
    // Envio
    @Column(name = "enviado_ao_smartwatch",nullable = false)
    private Boolean enviadoAoSmartwatch;
    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;
}
