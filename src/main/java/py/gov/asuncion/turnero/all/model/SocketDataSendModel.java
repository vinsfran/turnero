package py.gov.asuncion.turnero.all.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class SocketDataSendModel implements Serializable {

    private Integer nroOrden;
    private String nombreArchivoNroOrden;
    private String letra1;
    private String letra2;
    private String letra3;
    private String nombreArchivoLetra;
    private Integer nroTerminal;
    private String nombreArchivoNroTerminal;
    private String descripcionDependencia;
    private String nombreArchivoDescripcionDependencia;

}
