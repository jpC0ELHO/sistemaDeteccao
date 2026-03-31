package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.sensores;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.dtos.GenericMapper;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.devices.Sensor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SensorMapper implements GenericMapper<Sensor, SensorResponse, SensorRequest> {
    @Override
    public Sensor toEntity(SensorRequest request) {
        Objects.requireNonNull(request,"Request cannot be null");

        Sensor entity=new Sensor();
        entity.setNome(request.nome());
        entity.setAtivadoDesativado(request.ativadoDesativado());
        entity.setHorarioAcionamento(request.horarioAcionamento());
        entity.setMemoriaUsada(request.memoriaUsada());
        entity.setMemoriaDisponivel(request.memoriaDisponivel());
        entity.setValorDadosTransferencia(request.valorDadosTransferencia());
        entity.setDadosDescricao(request.dadosDescricao());
        entity.setRegiao(request.regiao());
        entity.setSensorTipo(request.sensoresTipo());
        return entity;
    }

    @Override
    public SensorResponse toResponse(Sensor entity) {
        Objects.requireNonNull(entity,"Entity cannot be null");
        return new SensorResponse(
                entity.getUuid(),
                entity.getNome(),
                entity.isAtivadoDesativado(),
                entity.getHorarioAcionamento(),
                entity.getMemoriaUsada(),
                entity.getMemoriaDisponivel(),
                entity.getValorDadosTransferencia(),
                entity.getDadosDescricao(),
                entity.getRegiao(),
                entity.getSensorTipo(),
                entity.getCreatedBy(),
                entity.getLastModifiedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
