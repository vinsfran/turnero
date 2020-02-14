package py.gov.asuncion.turnero.all.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import py.gov.asuncion.turnero.all.model.TicketModel;

/**
 *
 * @author vinsfran
 */
public class TicketPrintable implements Printable {

    private TicketModel ticketModel;

    public TicketPrintable(TicketModel ticketModel) {
        this.ticketModel = ticketModel;
    }

    @Override
    public int print(Graphics g, PageFormat f, int pageIndex) {
        if (pageIndex == 0) {

            Graphics2D g2 = (Graphics2D) g;

            Font tituloFont = new Font("Arial", Font.BOLD, 8);
            g2.setFont(tituloFont);
            g2.drawString("MUNICIPALIDAD DE ASUNCION", 20, 45);

            Font Fnumletra = new Font("Arial", Font.BOLD, 24);
            g2.setFont(Fnumletra);
            g2.drawString(ticketModel.getLetra() + "-" + ticketModel.getNumero(), 30, 70);
            
            Font Fmens = new Font("Arial", Font.BOLD, 8);
            g2.setFont(Fmens);
            g2.drawString(ticketModel.getDependenciaNombre(), 20, 90);

            return PAGE_EXISTS; //La página 1 existe y se imprimirá
        } else {
            return NO_SUCH_PAGE;        //No se imprimirán más páginas
        }
    }
}
