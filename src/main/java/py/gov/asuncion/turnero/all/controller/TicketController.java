package py.gov.asuncion.turnero.all.controller;

import py.gov.asuncion.turnero.all.data.dto.*;
import py.gov.asuncion.turnero.all.data.jdbcRepository.*;
import py.gov.asuncion.turnero.all.model.TicketModel;
import py.gov.asuncion.turnero.all.util.ConstantUtil;
import py.gov.asuncion.turnero.all.util.DateUtil;
import py.gov.asuncion.turnero.all.util.PrinterUtil;
import py.gov.asuncion.turnero.all.util.TicketPrintable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketController extends LogJdbcRepository {

    private DependenciaJdbcRepository dependenciaJdbcRepository;
    private OrdenJdbcRepository ordenJdbcRepository;
    private OrdenHistoricoJdbcRepository ordenHistoricoJdbcRepository;
    private String nombreClase;

    public TicketController() {
        this.nombreClase = TicketController.class.getName();
        dependenciaJdbcRepository = new DependenciaJdbcRepository();
        ordenJdbcRepository = new OrdenJdbcRepository();
        ordenHistoricoJdbcRepository = new OrdenHistoricoJdbcRepository();
    }

    public boolean borrarTablaOrden() {
        Orden orden = ordenJdbcRepository.getOneOrden();
        if (orden != null) {
            Date dateNow = new Date();
            if (!DateUtil.compararFechas(dateNow, orden.getFecha())) {
                if (!ordenJdbcRepository.deleteAll()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> getImpresoras() {
        List<String> impresoras = new ArrayList<>();
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService impresora : printServices) {
            impresoras.add(impresora.getName());
        }
        return impresoras;
    }

    public String generarTicket(Integer idDependencia, String impresoraSeleccionada) throws Exception {
        Dependencia dependencia = dependenciaJdbcRepository.getDependenciaById(idDependencia);
        Orden orden;
        if (dependencia != null) {
            OrdenJdbcRepository ordenJdbcRepository = new OrdenJdbcRepository();
            orden = ordenJdbcRepository.getOneOrdenByIdDependencia(dependencia.getIddependencia());
            if (orden == null) {
                orden = new Orden();
                orden.setNOrden(0);
                orden.setNLetra(1);
                orden.setAbec("A");
            }
            orden.setNOrden(orden.getNOrden() + 1);
            if (orden.getNOrden() > 100) {
                ParamJdbcRepository paramJdbcRepository = new ParamJdbcRepository();
                Param param = paramJdbcRepository.getParamByGrupoAndCodigo(ConstantUtil.GRUPO_ABCD, "MAXLETRAS");
                if (param != null) {
                    Integer maxLetrasLlamar = Integer.valueOf(param.getValor());
                    //Buscar letra actual (n_letra + 1)
                    AbecedarioJdbcRepository abecedarioJdbcRepository = new AbecedarioJdbcRepository();
                    Integer idLetra = orden.getNLetra() + 1;
                    if (idLetra > maxLetrasLlamar) {
                        idLetra = 1;
                    }
                    Abecedario abecedario = abecedarioJdbcRepository.getAbecedarioByIdAbecedario(idLetra);
                    orden.setNOrden(1);
                    orden.setNLetra(abecedario.getIdabc());
                    orden.setAbec(abecedario.getLetra());
                } else {
                    insertLog(this.nombreClase + ":generarTicket: Hubo un problema al obtener el parametro MaxLetras");
                    throw new Exception("Hubo un problema al obtener el parametro MaxLetras");
                }
            }
            orden.setIdOrden(ordenJdbcRepository.getMaxIdorden() + 1);
            orden.setClienteIdCliente(0);
            orden.setIdDependencia(dependencia.getIddependencia());
            orden.setEstado("P");
            orden.setFecha(new Date());
            orden.setAbreDependencia(dependencia.getAbreviatura());
            if (!ordenJdbcRepository.insertOrden(orden)) {
                insertLog(this.nombreClase + ":generarTicket: Hubo un problema al insertar el siguiente Turno");
                throw new Exception("Hubo un problema al insertar el siguiente Turno");
            }
            TicketModel ticketModel = new TicketModel();
            ticketModel.setDependenciaNombre(dependencia.getDescripcion());
            ticketModel.setLetra(orden.getAbreDependencia() + "-" + orden.getAbec());
            ticketModel.setNumero(orden.getNOrden());

            System.out.println("Ticket: " + ticketModel.toString());
            TicketPrintable ticketPrintable = new TicketPrintable(ticketModel);
            if (PrinterUtil.print(ticketPrintable, impresoraSeleccionada)) {
                OrdenHistorico ordenHistorico = new OrdenHistorico();
                ordenHistorico.setClienteIdCliente(0);
                ordenHistorico.setNOrden(ticketModel.getNumero());
                ordenHistorico.setAbec(ticketModel.getLetra());
                ordenHistorico.setIdDependencia(dependencia.getIddependencia());
                ordenHistorico.setFecha(new Date());
                if (!ordenHistoricoJdbcRepository.insertOrdenHistorico(ordenHistorico)) {
                    insertLog(this.nombreClase + ":generarTicket: Hubo un problema al insertar el siguiente Turno al Historico");
                    throw new Exception("Hubo un problema al insertar el siguiente Turno al Historico");
                }
            } else {
                throw new Exception("No se pudo imprimir el siguiente Ticket");
            }
        } else {
            insertLog(this.nombreClase + ":generarTicket: Hubo un problema al obtener la Dependencia: " + idDependencia);
            throw new Exception("Hubo un problema al obtener la Dependencia: " + idDependencia);
        }
        return orden.getAbreDependencia() + "-" + orden.getAbec() + orden.getNOrden();
    }

    public String getTotalPendientesByIdDependencia(Integer idDependencia) {
        return ordenJdbcRepository.totalPendientes(idDependencia).toString();
    }

}
