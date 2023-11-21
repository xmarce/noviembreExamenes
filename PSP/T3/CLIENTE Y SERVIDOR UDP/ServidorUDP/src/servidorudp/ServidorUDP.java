package servidorudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
public class ServidorUDP {
    public static void main(String[] args) throws SocketException, IOException {
        // Creamos Socket de comunicación a través
        // del puerto de tipo int 6789
        int puerto = 6789;
        DatagramSocket socketUDP =
            new DatagramSocket(puerto);
        // Creamos un array de bytes para recuperar
        // el mensaje del cliente
        byte[] buffer = new byte[1000];
        // Servidor queda a la espera del datagrama
        // que contiene el mensaje que enviará el cliente
        System.out.println("Esperando conexion del cliente...");
        // Recibimos datagram con el mensaje del
        // cliente
        DatagramPacket paqueteRecibido =
            new DatagramPacket(buffer, buffer.length);
        // Recibimos el paquete del cliente
        socketUDP.receive(paqueteRecibido);
        // String para recuperar el mensaje recibido
        // en el datagrama
        String mensajeRecibido = 
            new String(paqueteRecibido.getData());
        // Quitamos espacios sobrantes del mensaje
        String mensajeSinEspacios =
            mensajeRecibido.trim(); 
        System.out.println("Mensaje del cliente "
           + mensajeSinEspacios);
        // Extraemos la direccion y e puerto del cliente
        InetAddress hostCliente = paqueteRecibido.getAddress();
        int puertoCliente = paqueteRecibido.getPort();
        
        /***** Enviamos mensaje de bienvenida al cliente ***/
        // Creamos mensaje y convertimos en array de bytes
        String respuesta = "Bienvenido al Servidor";
        byte[] bytesRespuesta = respuesta.getBytes();
        // Creamos el datagrama de envío al cliente
        DatagramPacket paqueteRespuesta =
            new DatagramPacket(bytesRespuesta, 
            bytesRespuesta.length, hostCliente, puertoCliente);
        // Enviamos datagrama de respuesta al cliente
        socketUDP.send(paqueteRespuesta);
        // Cerramos socket de comunicacion
        socketUDP.close();
    }
}
