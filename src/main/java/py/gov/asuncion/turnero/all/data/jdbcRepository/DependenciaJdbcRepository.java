package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.Dependencia;

/**
 *
 * @author vinsfran
 */
public class DependenciaJdbcRepository {

    public Dependencia getDependenciaByDescripcion(String descripcion) {
        Conexion conexion = new Conexion();
        String sql = "SELECT iddependencia, descripcion, nombre_archivo FROM dependencia WHERE descripcion='" + descripcion.trim() + "'";
        System.out.println(sql);
        ResultSet rs = null;
        Dependencia dependencia = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            rs = statement.executeQuery(sql);
            rs.next();
            dependencia = new Dependencia();
            dependencia.setIddependencia(rs.getInt("iddependencia"));
            dependencia.setDescripcion(rs.getString("descripcion").trim());
            dependencia.setNombreArchivo(rs.getString("nombre_archivo").trim());
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dependencia;
    }

    public Dependencia getDependenciaById(Integer idDependencia) {
        Conexion conexion = new Conexion();
        String sql = "SELECT iddependencia, descripcion, nombre_archivo FROM dependencia WHERE iddependencia= " + idDependencia;
        System.out.println(sql);
        ResultSet rs = null;
        Dependencia dependencia = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            rs = statement.executeQuery(sql);
            rs.next();
            dependencia = new Dependencia();
            dependencia.setIddependencia(rs.getInt("iddependencia"));
            dependencia.setDescripcion(rs.getString("descripcion").trim());
            dependencia.setNombreArchivo(rs.getString("nombre_archivo").trim());
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dependencia;
    }

    public List<Dependencia> getDependenciasByEstado(boolean estado) {
        List<Dependencia> dependencias = new ArrayList<>();
        Conexion conexion = new Conexion();
//        String sql = "SELECT * FROM dependencia WHERE estado= " + estado;
        String sql = "SELECT * FROM dependencia";
        System.out.println(sql);
        ResultSet rs = null;
        Dependencia dependencia = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                dependencia = new Dependencia();
                dependencia.setIddependencia(rs.getInt("iddependencia"));
                dependencia.setDescripcion(rs.getString("descripcion").trim());
                dependencia.setNombreArchivo(rs.getString("nombre_archivo"));
//                dependencia.setEstado(rs.getBoolean("estado"));
                dependencias.add(dependencia);
            }
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dependencias;
    }

}
