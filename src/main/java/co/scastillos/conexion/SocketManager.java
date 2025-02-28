package co.scastillos.conexion;

import co.scastillos.dto.ConsultaSaldoDto;
import co.scastillos.dto.RespuestaSaldoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class SocketManager {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private ObjectMapper objectMapper;

    public SocketManager(String host, int puerto) throws IOException {
        this.socket = new Socket("localhost", 5000);
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.salida = new PrintWriter(socket.getOutputStream(), true);
        this.objectMapper = new ObjectMapper();
    }

    public void enviarConsulta(ConsultaSaldoDto consulta) throws IOException {
        String mensajeJson = objectMapper.writeValueAsString(consulta);
        salida.println(mensajeJson);
    }

    public RespuestaSaldoDto recibirRespuesta() throws IOException {
        String respuestaJson = entrada.readLine();
        return objectMapper.readValue(respuestaJson, RespuestaSaldoDto.class);
    }

    public void cerrarConexion() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
