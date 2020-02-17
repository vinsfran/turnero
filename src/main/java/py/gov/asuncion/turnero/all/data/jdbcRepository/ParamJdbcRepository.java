package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import py.gov.asuncion.turnero.all.data.dto.Param;
import py.gov.asuncion.turnero.all.conexion.Conexion;

/**
 *
 * @author vinsfran
 */
public class ParamJdbcRepository extends LogJdbcRepository {

    private String nombreClase;

    public ParamJdbcRepository() {
        this.nombreClase = ParamJdbcRepository.class.getName();
    }

    public Param getParamByGrupoAndCodigo(String grupo, String codigo) {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM params WHERE grupo= '" + grupo + "' AND codigo= '" + codigo + "'";
        System.out.println(sql);
        ResultSet rs = null;
        Param param = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            rs = statement.executeQuery(sql);
            rs.next();
            param = new Param();
            param.setId(rs.getInt("id"));
            param.setCodigo(rs.getString("codigo").trim());
            param.setValor(rs.getString("valor"));
            param.setGrupo(rs.getString("grupo"));
            param.setDescripcion(rs.getString("descripcion"));
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":getParamByGrupoAndCodigo: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return param;
    }

}
