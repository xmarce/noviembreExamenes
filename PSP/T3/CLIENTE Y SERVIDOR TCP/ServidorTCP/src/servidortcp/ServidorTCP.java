package servidortcp;
// (1) Importar java.net.*
import java.io.*;
import java.net.*;
public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        // (2) indicamos el puerto de comunicación
        int numPuerto = 6000;
        // (3) Creamos ServerSocket indicando el
        // puerto de comunicación
        ServerSocket servidor = 
            new ServerSocket(numPuerto);
        System.out.println("Esperando conexiones");
        // (4) Creamos Socket de comunicación
        // para esperar conexiones del cliente
        // que aceptaremos con el método .accept()
        Socket clienteSocket = servidor.accept();
        // (5) Proceso del Servidor con el cliente
        // Creamos canal de comunicación de salida
        // sobre el Socket clienteSocket
        DataOutputStream salida =
            new DataOutputStream(
                clienteSocket.getOutputStream());
        // Creamos mensaje de bienvenida
        String mensaje = "Bienvenido al Servidor";
        // Enviamos mensaje a través del canal
        // salida mediante método .writeUTF()
        salida.writeUTF(mensaje);
        // (6) Cerramos canal de comunicación
        salida.close();
        // Cerramos Socket y ServerSocket
        clienteSocket.close();
        servidor.close();
        System.out.println("Conexión Finalizada");
    }  
}
