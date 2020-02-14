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
public class MonitorJdbcRepository {

    public List<Monitor> getMonitorByEstado(boolean estado) {
        List<Monitor> monitores = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM monitores WHERE estado= " + estado;
        System.out.println(sql);
        ResultSet rs = null;
        Monitor monitor = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            rs = statement.executeQuery(sql);

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
            e.printStackTrace();
        }
        return monitores;
    }

}
