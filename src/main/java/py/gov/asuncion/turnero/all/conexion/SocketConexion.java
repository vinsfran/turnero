package py.gov.asuncion.turnero.all.conexion;

import py.gov.asuncion.turnero.all.data.dto.Monitor;
import py.gov.asuncion.turnero.all.data.jdbcRepository.MonitorJdbcRepository;
import py.gov.asuncion.turnero.all.model.SocketDataSendModel;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import py.gov.asuncion.turnero.all.data.jdbcRepository.LogJdbcRepository;

/**
 * @author cbaez02
 */
public class SocketConexion extends LogJdbcRepository {

    private String nombreClase;

    public SocketConexion() {
        this.nombreClase = SocketConexion.class.getName();
    }

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
                    String mensaje = this.nombreClase + ":send: " + e.getMessage();
                    insertLog(mensaje);
                    System.out.println(mensaje);
                }
            }
        } else {
            String mensaje = this.nombreClase + ":send: No existen Monitores registrados en la BD";
            insertLog(mensaje);
            System.out.println(mensaje);
        }

    }

}
