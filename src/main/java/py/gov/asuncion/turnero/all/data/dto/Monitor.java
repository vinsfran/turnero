package py.gov.asuncion.turnero.all.data.dto;

import lombok.Data;

/**
 *
 * @author vinsfran
 */
@Data
public class Monitor {

    private Integer id;

    private String ipMonitor;

    private Integer puertoMonitor;

    private boolean estado;

}
