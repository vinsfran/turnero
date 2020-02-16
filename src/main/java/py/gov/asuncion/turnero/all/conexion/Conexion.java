package py.gov.asuncion.turnero.all.conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;
import java.util.Properties;

import py.gov.asuncion.turnero.all.util.ConstantUtil;

/**
 * @author Cesar Baez
 */
public class Conexion {

    Properties config = new Properties();
    InputStream configInput = null;
    private Connection con = null;

    private String urlJdbc;
    private String userDb;
    private String passworDb;

    public Conexion() {
    }

    public Connection getConnection() {
        try {
//            File file = new File("config/config.properties");
//            File file = new File(Connection.class.getResource("/config/config.properties").getFile());
            File file = new File(Connection.class.getResource("/config/config_desa.properties").getFile());
            if (file.exists()) {
                configInput = new FileInputStream(file);
                config.load(configInput);
                urlJdbc = config.getProperty("urlJdbc");
                userDb = config.getProperty("userDb");
                passworDb = config.getProperty("passworDb");
                Class.forName(ConstantUtil.DRIVER_JDBC);
                con = DriverManager.getConnection(urlJdbc, userDb, passworDb);
            } else {
                System.out.println("Conexion:getConnection:ERROR: No existe Archivo");
            }
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
