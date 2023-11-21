//package ejercicio7t1psp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Suma {

    public static void main(String[] args) throws IOException {
        
        Scanner teclado = new Scanner(System.in);
        
        Process proceso = new ProcessBuilder("java", "SumaLectura").start();
        
        OutputStream salida = proceso.getOutputStream();
        
        System.out.println("Introduce el numero1: ");
        salida.write((teclado.nextLine() + "\n").getBytes());
        
        System.out.println("Introduce el numero2: ");
        salida.write((teclado.nextLine() + "\n").getBytes());
        
        salida.flush();

    
        InputStream entrada = proceso.getInputStream();
        
        int c;
        while ((c = entrada.read()) != -1) {
            System.out.print((char) c);
        }
        
        entrada.close();
        salida.close();
    }

}
