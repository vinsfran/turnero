package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.Abecedario;

/**
 *
 * @author vinsfran
 */
public class AbecedarioJdbcRepository extends LogJdbcRepository {

    private String nombreClase;

    public AbecedarioJdbcRepository() {
        this.nombreClase = AbecedarioJdbcRepository.class.getName();
    }

    public Abecedario getAbecedarioByIdAbecedario(Integer idabc) {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM abecedario WHERE idabc= " + idabc;
        System.out.println(sql);
        ResultSet rs = null;
        Abecedario abecedario = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            rs = statement.executeQuery(sql);
            rs.next();
            abecedario = new Abecedario();
            abecedario.setIdabc(rs.getInt("idabc"));
            abecedario.setLetra(rs.getString("letra").trim());
            abecedario.setEstado(rs.getString("estado"));
            abecedario.setNombreArchivo(rs.getString("nombre_archivo"));
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":getAbecedarioByIdAbecedario: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return abecedario;
    }
}
