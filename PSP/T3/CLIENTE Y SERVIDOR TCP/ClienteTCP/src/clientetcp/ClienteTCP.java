package clientetcp;
// (1) importamos java.net.*;
import java.io.*;
import java.net.*;
public class ClienteTCP {
    public static void main(String[] args) throws IOException {
        // (2) Creamos socket de comunicación
        Socket cliente =
            new Socket("localhost",6000);
        System.out.println("Conectado al Servidor");
        // (3) Proceso del cliente
        // Creamos canal de comunicación de entrada
        // para recibir mensaje del servidor
        DataInputStream entrada =
           new DataInputStream(
                cliente.getInputStream());
        // Recuperar mensaje del Servidor con readUTF()
        String mensaje = entrada.readUTF();
        // Mostramos el mensaje por pantalla
        System.out.println("Mensaje del Servidor: "+
            mensaje);
        // (4) Cerramos el canal de comunicación
        entrada.close();
        // (5) Cerramos el Socket
        cliente.close();
        System.out.println("Conexión con Servidor Cerrada");
    }   
}
