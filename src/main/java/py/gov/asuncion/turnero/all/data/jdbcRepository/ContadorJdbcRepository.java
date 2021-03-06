package py.gov.asuncion.turnero.all.data.jdbcRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import py.gov.asuncion.turnero.all.conexion.Conexion;
import py.gov.asuncion.turnero.all.data.dto.Contador;

/**
 *
 * @author vinsfran
 */
public class ContadorJdbcRepository extends LogJdbcRepository {

    private String nombreClase;

    public ContadorJdbcRepository() {
        this.nombreClase = ContadorJdbcRepository.class.getName();
    }

    public Contador getContadorByIdContador(Integer idContador) {
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM contador WHERE id_contador= " + idContador;
        System.out.println(sql);
        Contador contador = null;
        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            contador = new Contador();
            contador.setContador(rs.getInt("contador"));
            contador.setIdContador(rs.getInt("id_contador"));
            contador.setLetra(rs.getString("letra").trim());
            contador.setIdDependencia(rs.getInt("id_dependencia"));
            contador.setIdLetra(rs.getInt("id_letra"));
            contador.setMaxSecuencia(rs.getInt("max_secuencia"));
            rs.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":getContadorByIdContador: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        }
        return contador;
    }

    public boolean updateContador(Contador contador) {
        Conexion conexion = new Conexion();
        String sql = "UPDATE contador SET "
                + "contador= " + contador.getContador() + ", "
                + "letra= '" + contador.getLetra() + "', "
                + "id_letra= " + contador.getIdLetra() + ", "
                + "id_dependencia= " + contador.getIdDependencia() + " "
                + "WHERE id_contador = " + contador.getIdContador();
        System.out.println(sql);
        try {
            Statement statement = conexion.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conexion.close();
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":updateContador: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
            return false;
        }
        return true;
    }

}
