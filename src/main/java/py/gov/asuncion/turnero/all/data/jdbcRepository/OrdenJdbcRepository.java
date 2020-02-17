package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.Orden;
import py.gov.asuncion.turnero.all.util.ConstantUtil;
import py.gov.asuncion.turnero.all.util.DateUtil;

/**
 * @author vinsfran
 */
public class OrdenJdbcRepository extends LogJdbcRepository {

    private String nombreClase;

    public OrdenJdbcRepository() {
        this.nombreClase = OrdenJdbcRepository.class.getName();
    }

    public Orden getOneOrden() {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM orden ORDER BY idorden desc limit 1";
        System.out.println(sql);
        Orden orden;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            orden = new Orden();
            orden.setIdOrden(rs.getInt("idorden"));
            orden.setClienteIdCliente(rs.getInt("cliente_idcliente"));
            orden.setNOrden(rs.getInt("n_orden"));
            orden.setAbec(rs.getString("abec").trim());
            orden.setIdDependencia(rs.getInt("id_dependencia"));
            orden.setEstado(rs.getString("estado"));
            orden.setFecha(rs.getDate("fecha"));
            orden.setNLetra(rs.getInt("n_letra"));
            orden.setAbreDependencia(rs.getString("abre_dependencia"));
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            orden = null;
            String mensaje = this.nombreClase + ":getOneOrden: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return orden;
    }

    public Orden getOneOrdenByIdDependencia(Integer idDependencia) {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM orden WHERE id_dependencia = " + idDependencia + " ORDER BY idorden desc limit 1";
        System.out.println(sql);
        Orden orden = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            orden = new Orden();
            orden.setIdOrden(rs.getInt("idorden"));
            orden.setClienteIdCliente(rs.getInt("cliente_idcliente"));
            orden.setNOrden(rs.getInt("n_orden"));
            orden.setAbec(rs.getString("abec").trim());
            orden.setIdDependencia(rs.getInt("id_dependencia"));
            orden.setEstado(rs.getString("estado"));
            orden.setFecha(rs.getDate("fecha"));
            orden.setNLetra(rs.getInt("n_letra"));
            orden.setAbreDependencia(rs.getString("abre_dependencia"));
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            orden = null;
            String mensaje = this.nombreClase + ":getOneOrdenByIdDependencia: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return orden;
    }

    public Orden getByIdOrden(Integer idOrden) {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM orden WHERE idorden = " + idOrden;
        System.out.println(sql);
        Orden orden;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            orden = new Orden();
            orden.setIdOrden(rs.getInt("idorden"));
            orden.setClienteIdCliente(rs.getInt("cliente_idcliente"));
            orden.setNOrden(rs.getInt("n_orden"));
            orden.setAbec(rs.getString("abec").trim());
            orden.setIdDependencia(rs.getInt("id_dependencia"));
            orden.setEstado(rs.getString("estado"));
            orden.setFecha(rs.getDate("fecha"));
            orden.setNLetra(rs.getInt("n_letra"));
            orden.setAbreDependencia(rs.getString("abre_dependencia"));
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            orden = null;
            String mensaje = this.nombreClase + ":getByIdOrden: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return orden;
    }

    public Integer getMaxIdordenByDependenciaId(Integer idDependencia) {
        Conexion conexion = new Conexion();
        String sql = "SELECT MAX(idorden) as max FROM orden WHERE id_dependencia = " + idDependencia;
        System.out.println(sql);
        Integer max;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            max = rs.getInt("max");
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            max = 0;
            String mensaje = this.nombreClase + ":getMaxIdordenByDependenciaId: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return max;
    }

    public Integer getMaxIdorden() {
        Conexion conexion = new Conexion();
        String sql = "SELECT MAX(idorden) as max FROM orden";
        System.out.println(sql);
        Integer max;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            max = rs.getInt("max");
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            max = 0;
            String mensaje = this.nombreClase + ":getMaxIdorden: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return max;
    }

    public Integer getMinIdorden(Integer idDependencia) {
        Conexion conexion = new Conexion();
        String sql = "SELECT MIN(idorden) as min FROM orden WHERE id_dependencia= " + idDependencia + " AND estado='P'";
        System.out.println(sql);
        Integer min = 0;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            min = rs.getInt("min");
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":getMinIdorden: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return min;
    }

    public Integer totalPendientes(Integer idDependencia) {
        Integer totalPendientes = 0;
        List<Orden> ordenes = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM orden WHERE id_dependencia=" + idDependencia + " AND estado='P'";
        System.out.println(sql);
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Orden orden = new Orden();
                orden.setIdOrden(rs.getInt("idorden"));
                ordenes.add(orden);
            }
            rs.close();
            statement.close();
            conexion.close();
            if (!ordenes.isEmpty()) {
                totalPendientes = ordenes.size();
            }
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":totalPendientes: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return totalPendientes;
    }

    public boolean insertOrden(Orden orden) {
        Conexion conexion = new Conexion();
        String sql = "INSERT INTO orden (idorden, cliente_idcliente, n_orden, abec, id_dependencia, estado, fecha, n_letra, abre_dependencia) VALUES ("
                + orden.getIdOrden() + ", "
                + orden.getClienteIdCliente() + ", "
                + orden.getNOrden() + ", "
                + "'" + orden.getAbec() + "', "
                + orden.getIdDependencia() + ", "
                + "'" + orden.getEstado() + "', "
                + "'" + DateUtil.parseToString(orden.getFecha(), ConstantUtil.DATE_FORMAT_YYYY_MM_DD) + "',"
                + orden.getNLetra() + ", "
                + "'" + orden.getAbreDependencia() + "')";
        System.out.println(sql);
        try {
            Statement statement = conexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = "OrdenJdbcRepository:insertOrden:ERROR: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
            return false;
        }
        return true;
    }

    public boolean updateOrden(Orden orden) {
        Conexion conexion = new Conexion();
        String sql = "UPDATE orden SET estado='A', cliente_idcliente= " + orden.getClienteIdCliente() + " WHERE idorden = " + orden.getIdOrden();
        try {
            Statement statement = conexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":updateOrden: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
            return false;
        }
        return true;
    }

    public boolean deleteAll() {
        Conexion conexion = new Conexion();
        String sql = "DELETE FROM orden";
        System.out.println(sql);
        try {
            Statement statement = conexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":deleteAll: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
            return false;
        }
        return true;
    }

}
