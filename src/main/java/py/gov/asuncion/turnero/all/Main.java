package py.gov.asuncion.turnero.all;

import py.gov.asuncion.turnero.all.presenter.MonitorPresenter;
import py.gov.asuncion.turnero.all.vistas.MonitorView;

import javax.swing.*;

/**
 * @author vinsfran
 */
public class Main {

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            MonitorView monitorView = new MonitorView();
            monitorView.setPresenter(new MonitorPresenter(monitorView));
            monitorView.getPresenter().ejecutarConexion();
        });
    }

}
