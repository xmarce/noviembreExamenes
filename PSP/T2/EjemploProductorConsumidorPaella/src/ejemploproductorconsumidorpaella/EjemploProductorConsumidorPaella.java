package ejemploproductorconsumidorpaella;

import static java.lang.Thread.sleep;

public class EjemploProductorConsumidorPaella {
    public static void main(String[] args) {
        Contenedor miContenedor = new Contenedor();
        Productor hiloProductor = new Productor(miContenedor);
        Consumidor hiloConsumidor = new Consumidor(miContenedor);
        hiloProductor.start();
        hiloConsumidor.start();
    }
}

// Creamos clase compartida por los hilos
class Contenedor {
    private boolean racionLista = false;

    // Creamos método get() del hilo consumidor
    public synchronized void get() throws InterruptedException {
        // Si no hay ración lista, nos esperamos
        while (!racionLista) {
            wait();
        }
        // Simulamos el tiempo que tarda en recoger la ración
        sleep(100);
        // Indicamos que la ración ha sido recogida
        racionLista = false;
        System.out.println("Comensal recogió una ración");
        notify();
    }

    // Creamos método put que lanzará el hilo Productor
    public synchronized void put() throws InterruptedException {
        // Si hay ración lista, el Productor espera
        while (racionLista) {
            wait();
        }
        // Simulamos el tiempo que tarda en preparar la ración
        sleep(200);
        // Indicamos que la ración está lista para ser recogida
        racionLista = true;
        System.out.println("Cocinero preparó una ración");
        notify();
    }
}

// Hilo Productor que prepara las raciones
class Productor extends Thread {
    // atributo de la clase compartida
    private Contenedor miContenedor;

    // Constructor
    public Productor(Contenedor miContenedor) {
        this.miContenedor = miContenedor;
    }

    // Pruduce 500 raciones
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

// Hilo Consumidor que recoge las raciones preparadas
class Consumidor extends Thread {
    // atributo de la clase compartida
    private Contenedor miContenedor;

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
