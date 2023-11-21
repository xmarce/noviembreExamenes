package a1;

import static java.lang.Thread.sleep;

public class A1 {
    public static void main(String[] args) {
        
        Contenedor miContenedor = new Contenedor();
        Productor hiloProductor = new Productor(miContenedor);
        Consumidor hiloConsumidor = new Consumidor(miContenedor);
        hiloProductor.start();
        hiloConsumidor.start();
        
    }
}


class Contenedor {
    
    private boolean racionLista = false;

    public synchronized void get() throws InterruptedException {
        
        while (!racionLista) {
            wait();
        }
        
        sleep(100);
        
        racionLista = false;
        
        System.out.println("Comensal recogió una ración");
        notify();
    }

    
    public synchronized void put() throws InterruptedException {
        
        while (racionLista) {
            wait();
        }
        
        sleep(200);
        
        racionLista = true;
        System.out.println("Cocinero preparó una ración");
        notify();
    }
}


class Productor extends Thread {
    
    private Contenedor miContenedor;

    // Constructor
    public Productor(Contenedor miContenedor) {
        this.miContenedor = miContenedor;
    }

    // Produce 500 raciones
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            try {
                miContenedor.put();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Consumidor extends Thread {
    
    private Contenedor miContenedor;

     // Constructor
    public Consumidor(Contenedor miContenedor) {
        this.miContenedor = miContenedor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            try {
                miContenedor.get();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}