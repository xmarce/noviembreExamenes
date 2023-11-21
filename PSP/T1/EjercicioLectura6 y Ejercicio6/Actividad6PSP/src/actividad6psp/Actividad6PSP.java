//package actividad6psp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Actividad6PSP {

    public static void main(String[] args) throws IOException {
        
        //Creamos proceso que lanza EjemploLectura6PSP
        Process proceso = new ProcessBuilder("java", "ActividadLectura6PSP").start();
        
        
        //Creamos flujo de salida para enviarle el mensaje
        OutputStream salida = proceso.getOutputStream();
        
        
        //Le mandamos el texto a enviar a EjemploLectura con .write()
        salida.write("5\n".getBytes());
        
        
        //Vaciar el buffer
        salida.flush();

        //Crear flujo de entrada para leer lo que devuelve la ejecucion del proceso
        //Establecemos flujo de entrada de comunicacion sobre el proceso
        InputStream entrada = proceso.getInputStream();
        
        
        //Leemos caracter a caracter lo que nos devuelve el proceso
        int c;
        while ((c = entrada.read()) != -1) {
            System.out.println((char) c);
        }
        
        
        //Cerrar flujo de comunicacion
        entrada.close();

    }

}
