package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.Monitor;

/**
 *
 * @author vinsfran
 */
public class MonitorJdbcRepository extends LogJdbcRepository {

    private String nombreClase;

    public MonitorJdbcRepository() {
        this.nombreClase = MonitorJdbcRepository.class.getName();
    }

    public List<Monitor> getMonitorByEstado(boolean estado) {
        List<Monitor> monitores = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM monitores WHERE estado= " + estado;
        System.out.println(sql);
        Monitor monitor = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                monitor = new Monitor();
                monitor.setId(rs.getInt("id"));
                monitor.setIpMonitor(rs.getString("ip_monitor").trim());
                monitor.setPuertoMonitor(rs.getInt("puerto_monitor"));
                monitor.setEstado(rs.getBoolean("estado"));
                monitores.add(monitor);
            }
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":getMonitorByEstado: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return monitores;
    }

}
