package py.gov.asuncion.turnero.all.data.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author vinsfran
 */
@Data
public class Orden {

    private Integer idOrden;

    private Integer clienteIdCliente;

    private Integer nOrden;

    private String abec;

    private Integer idDependencia;

    private String estado;

    private Date fecha;
    
    private Integer nLetra;
    
    private String abreDependencia;

}
