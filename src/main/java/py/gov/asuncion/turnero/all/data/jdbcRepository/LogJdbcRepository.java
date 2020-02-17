package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.Statement;
import py.gov.asuncion.turnero.all.conexion.Conexion;

/**
 *
 * @author vinsfran
 */
public class LogJdbcRepository {

    public boolean insertLog(String mensaje) {
        Conexion conexion = new Conexion();
        String sql = "INSERT INTO logs (log) VALUES ('" + mensaje + "')";
        System.out.println(sql);
        try {
            Statement statement = conexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
