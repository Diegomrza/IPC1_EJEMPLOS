package controllers;

import models.EnemigoModel;
import java.util.Random;

// hilo que genera un enemigo nuevo cada cierto tiempo mientras la partida sigue activa.
// cada enemigo que genera arranca ademas su propio hilo de movimiento
public class GeneradorEnemigosController extends Thread {

    private static final long SLEEP_ENTRE_ENEMIGOS = 1500L;

    private EnemigosVectorController enemigos;
    private int anchoPantalla;
    private int altoPantalla;
    private volatile boolean jugando;
    private Random random;

    public GeneradorEnemigosController(EnemigosVectorController enemigos, int anchoPantalla, int altoPantalla) {
        this.enemigos = enemigos;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.jugando = true;
        this.random = new Random();
    }

    public void detener() {
        this.jugando = false;
        this.interrupt();
    }

    @Override
    public void run() {
        while (this.jugando) {
            // Este hilo solo crea enemigos. El movimiento de cada enemigo queda
            // a cargo del hilo que se inicia inmediatamente despues.
            int y = this.random.nextInt(this.altoPantalla - 30);
            EnemigoModel enemigo = new EnemigoModel(this.anchoPantalla, y);
            this.enemigos.agregar(enemigo);

            EnemigoHiloController hiloEnemigo = new EnemigoHiloController(enemigo);
            hiloEnemigo.start();

            try {
                // Controla la frecuencia de aparicion de enemigos.
                Thread.sleep(SLEEP_ENTRE_ENEMIGOS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
