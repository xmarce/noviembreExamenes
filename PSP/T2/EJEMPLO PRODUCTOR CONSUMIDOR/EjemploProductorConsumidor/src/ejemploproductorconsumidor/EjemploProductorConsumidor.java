package ejemploproductorconsumidor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EjemploProductorConsumidor {
    public static void main(String[] args) {
        Contenedor miContenedor = new Contenedor();
        Productor hiloProductor = new Productor(miContenedor);
        Consumidor hiloConsumidor =  new Consumidor(miContenedor);
        hiloProductor.start();
        hiloConsumidor.start();
    }
    
}

// Creamos clase compartida por los hilos
class Contenedor {
    private int dato;
    private boolean hayDato = false;
    
    // Creamos método get() del hilo consumidor
    public synchronized int get() throws InterruptedException{
        // Si no hay dato, nos esperamos
        if(hayDato==false){
            wait();
        }
        sleep(2000);
        // Si hay datos lo mostramos e
        // indicamos que ya no hay dato
        // y despertamos al productor para 
        // que ponga otro
        System.out.println("Consumidor dato: "
            + this.dato);
        hayDato = false;
        notifyAll();
        return dato;
    }
    
    // Creamos método put que lanzará el 
    // el hilo Productor
    public synchronized void put(int valor) throws InterruptedException{
        // Si hay dato el Productor espera
        if(hayDato==true){
            wait();
        }
        sleep(2000);
        // Produce dato y lo almacena en dato
        this.dato=valor;
        System.out.println("Productor dato: "+
            this.dato);
        // Indica que ha producido dato y 
        // despierta al consumidor
        hayDato=true;
        notifyAll();
    }  
}

// Hilo Productor que produce numeros enteros
class Productor extends Thread{
    // atributo de la clase compartida
    private Contenedor miContenedor;
    // Constructor
    public Productor(Contenedor miContenedor) {
        this.miContenedor = miContenedor;
    }
    // Pruduce 10 numeros (0-9)
    @Override
    public void run(){
        for(int i=0;i<10;i++){
            try {
                miContenedor.put(i);
            } catch (InterruptedException ex) {
            }
        }
    }
    
}

// Hilo Consumidor que consume numeros producidos por
// el productor
class Consumidor extends Thread{
    // atributo de la clase compartida
    private Contenedor miContenedor;

    public Consumidor(Contenedor miContenedor) {
        this.miContenedor = miContenedor;
    }

    @Override
    public void run(){
        for(int i=0; i<10;i++){
            try {
                miContenedor.get();
            } catch (InterruptedException ex) {
            }
        }
    }
}
