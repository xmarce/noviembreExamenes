package clienteudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        // Creamos mensaje y convertimos a bytes
        String mensaje = "Hola Servidor";
        byte[] mensajeBytes = mensaje.getBytes();
        // Creamos instancia de host servidor y 
        // puerto
        InetAddress hostServidor =
            InetAddress.getByName("localhost");
        int puertoServidor = 6789;
        
        //Creamos Socket de comunicación UDP
        DatagramSocket socketUDP =
            new DatagramSocket();
        // Creamos datagrama de envío
        DatagramPacket paqueteEnvio =
           new DatagramPacket(mensajeBytes,
           mensajeBytes.length, hostServidor, 
           puertoServidor);
        // Realizamos el envío del paquete al servidor
        socketUDP.send(paqueteEnvio);
        
        //Recibimos mensaje de bienvenida del servidor
        // Creamos array de bytes para recuperar el
        // mensaje
        byte[] buffer = new byte[1000];
        // Creamos el datagrama de respuesta al cliente
        DatagramPacket paqueteRespuesta =
            new DatagramPacket(buffer, buffer.length);
        // Recuperamos el mensaje del servidor 
        // en el paquete de respuesta
        socketUDP.receive(paqueteRespuesta);
        // Obtenemos el mensaje del datagrama
        String mensajeRespuesta = 
             new String(paqueteRespuesta.getData());
        // Quitamos espacios sobrantes del mensaje
        String mensajeSinEspacios = 
            mensajeRespuesta.trim();
        System.out.println("Mensaje del Servidor: "
            + mensajeSinEspacios);
        // Cerramos socket
        socketUDP.close();
    }    
}
