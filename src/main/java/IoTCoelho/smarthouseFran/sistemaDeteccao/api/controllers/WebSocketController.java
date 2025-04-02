package IoTCoelho.smarthouseFran.sistemaDeteccao.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Evento;
import IoTCoelho.smarthouseFran.sistemaDeteccao.domain.entities.Leitura;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
public class WebSocketController {
    @MessageMapping("/alerta")
    @SendTo("/topic/alertas")
    public Evento enviarAlerta(Evento evento){
        log.info("Alerta disparado: {}",evento);
        return evento;
    }
    @MessageMapping("/afericao")
    @SendTo("/topic/afericao")
    public Leitura afericao(Leitura leitura){
        log.info("Aferição de Sensores: {}",leitura);
        return leitura;
    }
}
