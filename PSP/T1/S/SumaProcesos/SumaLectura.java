//package ejerciciolectura7t1psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumaLectura {

    public static void main(String[] args) throws IOException {
       
        
        InputStreamReader entrada = new InputStreamReader(System.in);
        
        
        BufferedReader bufferEntrada = new BufferedReader(entrada);
        
        
        String num1T = bufferEntrada.readLine();

        String num2T = bufferEntrada.readLine();
        
        
        int total = 0;
        int numero1 = Integer.parseInt(num1T);
        int numero2 = Integer.parseInt(num2T);

        total = numero1 + numero2;

        System.out.print("Salida Suma: " + total);

        
        bufferEntrada.close();
        entrada.close();
    }

}
