package py.gov.asuncion.turnero.all.presenter;

import py.gov.asuncion.turnero.all.data.dto.Param;
import py.gov.asuncion.turnero.all.data.jdbcRepository.ParamJdbcRepository;
import py.gov.asuncion.turnero.all.model.SocketDataSendModel;
import py.gov.asuncion.turnero.all.util.ConstantUtil;
import py.gov.asuncion.turnero.all.util.ReproductorUtil;
import py.gov.asuncion.turnero.all.view.MonitorView;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import py.gov.asuncion.turnero.all.data.jdbcRepository.LogJdbcRepository;

public class MonitorPresenter extends LogJdbcRepository {

    private ParamJdbcRepository paramJdbcRepository;
    private MonitorView monitorView;
    private Param param;
    private String nombreClase;

    public MonitorPresenter(MonitorView monitorView) {
        this.nombreClase = MonitorPresenter.class.getName();
        String paramCodigo = ConstantUtil.CODIGO_PATHSONIDOLINUX;
        String sSistemaOperativo = System.getProperty("os.name");
        System.out.println(sSistemaOperativo);
        if (!sSistemaOperativo.equals(ConstantUtil.SOLINUX)) {
            paramCodigo = ConstantUtil.CODIGO_PATHSONIDOWIN;
        }
        this.monitorView = monitorView;
        this.paramJdbcRepository = new ParamJdbcRepository();
        this.param = paramJdbcRepository.getParamByGrupoAndCodigo(ConstantUtil.GRUPO_PATH, paramCodigo);
    }

    public void ejecutarConexion() {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                runServer();
            }
        });
        hilo.start();
    }

    private void runServer() {
        int port = ConstantUtil.SERVER_PORT;
        ServerSocket server = null;
        Socket socket = null;

        try {
            //Se crea el ServerSocket
            server = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            //Bucle infinito para esperar conexiones
            while (true) {
                socket = server.accept();
                System.out.println("Cliente con la IP: " + socket.getInetAddress().getHostName() + " conectado");
                InputStream input = socket.getInputStream();
                ObjectInputStream paquete = new ObjectInputStream(input);
                SocketDataSendModel paqueteRecibido = (SocketDataSendModel) paquete.readObject();
                monitorView.updatePantalla(paqueteRecibido);
                reproducirAudio(paqueteRecibido);
            }
        } catch (Exception e) {
            String mensaje = this.nombreClase + ":runServer: " + e.getMessage();
            insertLog(mensaje);
            System.out.println(mensaje);
        } finally {
            try {
                socket.close();
                server.close();
            } catch (Exception e) {
                String mensaje = this.nombreClase + ":runServer: " + e.getMessage();
                insertLog(mensaje);
                System.out.println(mensaje);
            }
        }
    }

    private void reproducirAudio(SocketDataSendModel paqueteRecibido) {
        if (param != null) {
            ReproductorUtil reproductor = new ReproductorUtil();
            reproductor.play(param.getValor() + "timbre1.wav");
            reproductor.play(param.getValor() + "ticket.wav");
            reproductor.play(param.getValor() + "letras/" + paqueteRecibido.getLetra1().toLowerCase() + ".wav");
            reproductor.play(param.getValor() + "letras/" + paqueteRecibido.getLetra2().toLowerCase() + ".wav");
            reproductor.play(param.getValor() + "letras/" + paqueteRecibido.getNombreArchivoLetra());
            reproductor.play(param.getValor() + "numeros/" + paqueteRecibido.getNombreArchivoNroOrden());
            reproductor.play(param.getValor() + "dependencias/" + paqueteRecibido.getNombreArchivoDescripcionDependencia());
            reproductor.play(param.getValor() + "box.wav");
            reproductor.play(param.getValor() + "numeros/" + paqueteRecibido.getNombreArchivoNroTerminal());
        } else {
            String mensaje = this.nombreClase + ":reproducirAudio: param NULL";
            insertLog(mensaje);
            System.out.println(mensaje);
        }

    }
}
