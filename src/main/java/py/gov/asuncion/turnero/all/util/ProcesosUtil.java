package py.gov.asuncion.turnero.all.util;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Cesar
 */
public class ProcesosUtil {

    public static ArrayList resultContador(ResultSet rs) throws SQLException {
        Integer nc = 0;
        //número de columnas (campos) de la consula SQL            	  
        nc = rs.getMetaData().getColumnCount();
        ArrayList val = new ArrayList();
        while (rs.next()) {
            for (int i = 1; i <= nc; i++) {
                val.add(rs.getObject(i));
            }
        }
        return val;
    }

    public static ArrayList resultArrayList(ResultSet rs) throws SQLException {
        ArrayList val = new ArrayList();
        while (rs.next()) {
            for (int i = 1; i <= 16; i++) {//21     
                val.add(rs.getObject(i));
            }
        }
        return val;
    }

    public static boolean valFilas(String cantFila) {
        Integer val = Integer.parseInt(cantFila);
        if (val == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String valNumMenos10(Integer nro) {
        String vNum;
        if (nro == null) {
            vNum = "00";
        } else {
            if (nro < 10) {
                vNum = "0" + nro;
            } else {
                vNum = String.valueOf(nro);
            }
        }
        return vNum;
    }

}
