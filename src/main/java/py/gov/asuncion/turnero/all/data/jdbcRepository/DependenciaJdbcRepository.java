package py.gov.asuncion.turnero.all.data.jdbcRepository;

import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.Dependencia;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vinsfran
 */
public class DependenciaJdbcRepository extends LogJdbcRepository {

    private String nombreClase;

    public DependenciaJdbcRepository() {
        this.nombreClase = DependenciaJdbcRepository.class.getName();
    }

    public Dependencia getDependenciaByDescripcion(String descripcion) {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM dependencia WHERE descripcion='" + descripcion.trim() + "'";
        System.out.println(sql);
        Dependencia dependencia = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            dependencia = new Dependencia();
            dependencia.setIddependencia(rs.getInt("iddependencia"));
            dependencia.setDescripcion(rs.getString("descripcion").trim());
            dependencia.setNombreArchivo(rs.getString("nombre_archivo").trim());
            dependencia.setAbreviatura(rs.getString("abreviatura").trim());
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":getDependenciaByDescripcion: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return dependencia;
    }

    public Dependencia getDependenciaById(Integer idDependencia) {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM dependencia WHERE iddependencia= " + idDependencia;
        System.out.println(sql);
        Dependencia dependencia;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            dependencia = new Dependencia();
            dependencia.setIddependencia(rs.getInt("iddependencia"));
            dependencia.setDescripcion(rs.getString("descripcion").trim());
            dependencia.setNombreArchivo(rs.getString("nombre_archivo").trim());
            dependencia.setAbreviatura(rs.getString("abreviatura").trim());
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            dependencia = null;
            String mensaje = this.nombreClase + ":getDependenciaById: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return dependencia;
    }

    public List<Dependencia> getDependenciasByEstado(boolean estado) {
        List<Dependencia> dependencias = new ArrayList<>();
        Conexion conexion = new Conexion();
//        String sql = "SELECT * FROM dependencia WHERE estado= " + estado;
        String sql = "SELECT * FROM dependencia";
        System.out.println(sql);
        Dependencia dependencia = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                dependencia = new Dependencia();
                dependencia.setIddependencia(rs.getInt("iddependencia"));
                dependencia.setDescripcion(rs.getString("descripcion").trim());
                dependencia.setNombreArchivo(rs.getString("nombre_archivo").trim());
                dependencia.setAbreviatura(rs.getString("abreviatura").trim());
                dependencias.add(dependencia);
            }
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":getDependenciasByEstado: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return dependencias;
    }

}
