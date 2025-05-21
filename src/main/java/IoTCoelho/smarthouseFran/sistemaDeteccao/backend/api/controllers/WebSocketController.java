package IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.controllers;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Evento;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Leitura;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/alerta")
    @SendTo("/topic/alertas")
    public Evento enviarAlerta(Evento evento) {
        log.info("Alerta disparado: {}", evento);
        return evento;
    }

    @MessageMapping("/afericao")
    @SendTo("/topic/afericao")
    public Leitura afericao(Leitura leitura) {
        log.info("Aferição de Sensores: {}", leitura);
        return leitura;
    }

    public void enviarEvento(Evento evento) {
        messagingTemplate.convertAndSend("/topic/alertas", evento);
        log.info("Evento enviado via WebSocket: {}", evento);
    }


    public void enviarAfericao(Leitura leitura) {
        messagingTemplate.convertAndSend("/topic/afericao", leitura);
        log.info("Aferição enviada via WebSocket: {}", leitura);
    }
}
