package py.gov.asuncion.turnero.all.data.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author vinsfran
 */
@Data
public class OrdenHistorico {

    private Integer clienteIdCliente;

    private Integer nOrden;

    private String abec;

    private Integer idDependencia;

    private Date fecha;

    private Integer idordenh;

}
