package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.Abecedario;

/**
 *
 * @author vinsfran
 */
public class AbecedarioJdbcRepository {

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
            e.printStackTrace();
        }
        return abecedario;
    }

//    public boolean updateAbecedario(Abecedario abecedario) {
//        Conexion conexion = new Conexion();
//        String sql = "UPDATE abecedario SET estado='A', cliente_idcliente= " + abecedario.getIdCliente() + " WHERE idabecedario = " + abecedario.getIdAbecedario();
//        try {
//            Statement statement = conexion.getConnection().createStatement();
//            statement.executeUpdate(sql);
//            statement.close();
//            conexion.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
}
