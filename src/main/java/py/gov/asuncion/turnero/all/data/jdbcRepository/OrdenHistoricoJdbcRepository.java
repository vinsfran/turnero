package py.gov.asuncion.turnero.all.data.jdbcRepository;

import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.OrdenHistorico;
import py.gov.asuncion.turnero.all.util.ConstantUtil;
import py.gov.asuncion.turnero.all.util.DateUtil;

import java.sql.Statement;

/**
 * @author vinsfran
 */
public class OrdenHistoricoJdbcRepository extends LogJdbcRepository {

    private String nombreClase;

    public OrdenHistoricoJdbcRepository() {
        this.nombreClase = OrdenHistoricoJdbcRepository.class.getName();
    }

    public boolean insertOrdenHistorico(OrdenHistorico ordenHistorico) {
        Conexion conexion = new Conexion();
        String sql = "INSERT INTO ordenhistorico (cliente_idcliente, n_orden, abec, id_dependencia, fecha) VALUES ("
                + ordenHistorico.getClienteIdCliente() + ", "
                + ordenHistorico.getNOrden() + ", "
                + "'" + ordenHistorico.getAbec() + "', "
                + ordenHistorico.getIdDependencia() + ", "
                + "'" + DateUtil.parseToString(ordenHistorico.getFecha(), ConstantUtil.DATE_FORMAT_YYYY_MM_DD) + "')";
        System.out.println(sql);
        try {
            Statement statement = conexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":insertOrdenHistorico: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
            return false;
        }
        return true;
    }

}
