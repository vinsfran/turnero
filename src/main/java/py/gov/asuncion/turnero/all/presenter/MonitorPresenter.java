package py.gov.asuncion.turnero.all.presenter;

import py.gov.asuncion.turnero.all.model.SocketDataSendModel;
import py.gov.asuncion.turnero.all.util.ConstantUtil;
import py.gov.asuncion.turnero.all.util.ReproductorUtil;
import py.gov.asuncion.turnero.all.vistas.Monitor;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitorPresenter {


    public void run() {
        int port = ConstantUtil.SERVER_PORT;
        ServerSocket server = null;
        Socket socket = null;

        try {
            //Se crea el ServerSocket
            server = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            String nroOrden, nroTerminal, descripcionDependencia;
            String letra1;
            String letra2;
            String letra3;
            //Bucle infinito para esperar conexiones
            while (true) {
                socket = server.accept();
                System.out.println("Cliente con la IP: " + socket.getInetAddress().getHostName() + " conectado");
                InputStream input = socket.getInputStream();
                ObjectInputStream paquete = new ObjectInputStream(input);
                SocketDataSendModel paqueteRecibido = (SocketDataSendModel) paquete.readObject();
                System.out.println("SocketDataSendModel: " + paqueteRecibido.toString());
                nroOrden = paqueteRecibido.getNroOrden().toString();
                letra1 = paqueteRecibido.getLetra1().trim();
                letra2 = paqueteRecibido.getLetra2().trim();
                letra3 = paqueteRecibido.getLetra3();
                nroTerminal = paqueteRecibido.getNroTerminal().toString();
                descripcionDependencia = paqueteRecibido.getDescripcionDependencia().trim();

                if (Integer.valueOf(nroOrden) < 10) {
                    nroOrden = "0" + nroOrden;
                }

                TextArea2.append("\n" + " " + letra1 + letra2 + "-" + letra3 + nroOrden + " - " + descripcionDependencia + " - BOX" + nroTerminal);
                TextArea3.append("\n" + "TICKET  " + letra1 + letra2 + "-" + letra3 + nroOrden);
                TextArea5.append("\n" + descripcionDependencia);
                TextArea6.append("\n" + "BOX" + nroTerminal);
                cambiaColor();

                pos = pos + 1;
                borradoFilas1();
                borradoUltimoLlamado();
                ReproductorUtil reproductor = new ReproductorUtil();
                reproductor.play(pathSonido + "timbre1.wav");
                reproductor.play(pathSonido + "ticket.wav");
                reproductor.play(pathSonido + "letras/" + letra1.toLowerCase() + ".wav");
                reproductor.play(pathSonido + "letras/" + letra2.toLowerCase() + ".wav");
                reproductor.play(pathSonido + "letras/" + paqueteRecibido.getNombreArchivoLetra());
                reproductor.play(pathSonido + "numeros/" + paqueteRecibido.getNombreArchivoNroOrden());
                reproductor.play(pathSonido + "dependencias/" + paqueteRecibido.getNombreArchivoDescripcionDependencia());
                reproductor.play(pathSonido + "box.wav");
                reproductor.play(pathSonido + "numeros/" + paqueteRecibido.getNombreArchivoNroTerminal());
            }
        } catch (Exception ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
                server.close();
            } catch (Exception ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
