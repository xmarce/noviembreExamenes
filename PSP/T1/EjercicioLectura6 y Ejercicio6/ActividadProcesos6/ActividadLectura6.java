//package actividadlectura6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActividadLectura6 {

    public static void main(String[] args) throws IOException {
        //Creamos flujo de datos para recoger los datos enviados por el otro programa
        InputStreamReader entrada = new InputStreamReader(System.in);
        //Creamos buffer de entrada
        BufferedReader bufferEntrada = new BufferedReader(entrada);
        //Leemos la linea de informacion
        String linea = bufferEntrada.readLine();

        //Sacar el factorial de 5
        int factorial = 1;
        int numero = Integer.parseInt(linea);

        while (numero != 0) {
            factorial = factorial * numero;
            numero--;
        }
        System.out.println("Salida Factorial: " + factorial);
        //Cerramos buffer y flujo
        bufferEntrada.close();
        entrada.close();

    }

}
