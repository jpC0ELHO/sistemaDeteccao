package IoTCoelho.smarthouseFran.sistemaDeteccao.frontend.servlets;

import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.api.services.leitura.LeituraService;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Leitura;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Localizacao;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.Sensores;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.entities.enums.DeteccaoTipo;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories.LocalizacaoRepository;
import IoTCoelho.smarthouseFran.sistemaDeteccao.backend.domain.repositories.SensoresRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet("/leitura")
public class LeituraServlet extends HttpServlet {

    @Autowired
    private LeituraService leituraService;

    @Autowired
    private SensoresRepository sensorRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int valorAferido = Integer.parseInt(request.getParameter("valorAferido"));
        String tipo = request.getParameter("deteccaoTipo");
        UUID sensorId = UUID.fromString(request.getParameter("sensorId"));
        UUID localId = UUID.fromString(request.getParameter("localId"));

        // Obter sensor e local
        Sensores sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));
        Localizacao local = localizacaoRepository.findById(localId)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        // Criar leitura
        Leitura leitura = new Leitura();
        leitura.setValorAferido(valorAferido);
        leitura.setHorarioDetec(LocalDateTime.now());
        leitura.setDeteccaoSimNao(valorAferido > 50); // Exemplo de regra
        leitura.setDeteccaoTipo(DeteccaoTipo.valueOf(tipo));
        leitura.setSensor(sensor);
        leitura.setLocal(local);

        leituraService.saveLeitura(leitura);
        response.sendRedirect("sucesso.jsp");
    }
}
