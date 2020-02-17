package py.gov.asuncion.turnero.all.conexion;

import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;

import py.gov.asuncion.turnero.all.util.ConstantUtil;

/**
 * @author Cesar Baez
 */
public class Conexion {

    InputStream configInput = null;
    private Connection con = null;

    public Conexion() {
    }

    public Connection getConnection() {
        try {
            Class.forName(ConstantUtil.DRIVER_JDBC);
            con = DriverManager.getConnection(ConstantUtil.URL_JDBC_PRODU, ConstantUtil.USER_PRODU, ConstantUtil.PASWORD_PRODU);
//            con = DriverManager.getConnection(ConstantUtil.URL_JDBC_DESA, ConstantUtil.USER_DESA, ConstantUtil.PASWORD_DESA);
        } catch (Exception e) {
            System.out.println("Conexion:getConnection:ERROR: " + e.getMessage());
        }
        return con;
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
