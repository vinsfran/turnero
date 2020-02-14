package py.gov.asuncion.turnero.all.conexion;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import py.gov.asuncion.turnero.all.data.dto.Monitor;
import py.gov.asuncion.turnero.all.data.jdbcRepository.MonitorJdbcRepository;
import py.gov.asuncion.turnero.all.model.SocketDataSendModel;

/**
 *
 * @author cbaez02
 */
public class SocketConexion {

    public void send(SocketDataSendModel socketDataSendModel) {
        MonitorJdbcRepository monitorJdbcRepository = new MonitorJdbcRepository();
        List<Monitor> monitores = monitorJdbcRepository.getMonitorByEstado(true);

        if (!monitores.isEmpty()) {
            for (Monitor monitor : monitores) {
                try {
                    Socket socket = new Socket(monitor.getIpMonitor(), monitor.getPuertoMonitor());
                    ObjectOutputStream paqueteDatos = new ObjectOutputStream(socket.getOutputStream());
                    paqueteDatos.writeObject(socketDataSendModel);
                    socket.close();
                } catch (Exception e) {
                    System.out.println("Error al enviar por socket: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No existen Monitores registrados en la BD");
        }

    }

}
