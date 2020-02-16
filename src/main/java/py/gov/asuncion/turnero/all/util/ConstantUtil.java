package py.gov.asuncion.turnero.all.util;

/**
 * @author vinsfran
 */
public interface ConstantUtil {

    String FILE_PATH = "/turnero/sonido/";
    String SERVER_IP = "126.10.10.189";
    Integer SERVER_PORT = 6666;
    String URL_JDBC = "jdbc:postgresql://ateneadev.asuncion.gov.py:5432/sima_db?currentSchema=turnos";
    String DRIVER_JDBC = "org.postgresql.Driver";
    String USER = "sima";
    String PASWORD = "Tics2019";
    String SOLINUX = "Linux";
    String CODIGO_PATHSONIDOLINUX = "PATHSONIDOLINUX";
    String CODIGO_PATHSONIDOWIN = "PATHSONIDOWIN";
    String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    String GRUPO_ABCD = "ABCD";

    Integer ID_CAJA = 1;
    Integer ID_FACTURACION = 2;
    Integer ID_GESTION_TRIBUTARIA = 3;
    Integer ID_PATENTES = 4;
    Integer ID_GRANDES_CONTRIBUYENTES = 5;
    Integer ID_UNIDAD_DE_INMUEBLES = 6;
    Integer ID_ATENCION_AL_CONTRIBUYUNTES = 7;
}
