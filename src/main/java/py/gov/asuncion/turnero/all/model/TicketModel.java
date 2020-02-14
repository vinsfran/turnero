package py.gov.asuncion.turnero.all.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author vinsfran
 */
@Data
public class TicketModel implements Serializable {

    private Integer numero;
    private String letra;
    private String dependenciaNombre;

}
