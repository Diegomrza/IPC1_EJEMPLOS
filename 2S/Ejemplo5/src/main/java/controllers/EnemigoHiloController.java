package controllers;

import models.EnemigoModel;

// hilo independiente por cada enemigo, nace en X maximo y viaja hacia X minimo hasta salir
// de la pantalla o hasta que el jugador lo destruya
public class EnemigoHiloController extends Thread {

    private static final long SLEEP_MOVIMIENTO = 20L;

    private EnemigoModel enemigo;

    public EnemigoHiloController(EnemigoModel enemigo) {
        this.enemigo = enemigo;
    }

    @Override
    public void run() {
        // El ciclo termina cuando el enemigo sale de pantalla o una colision
        // lo marca como inactivo.
        while (this.enemigo.isActivo() && this.enemigo.getX() + this.enemigo.getAncho() > 0) {
            this.enemigo.mover();
            try {
                // La pausa controla la frecuencia de movimiento y evita un ciclo
                // que consuma el procesador continuamente.
                Thread.sleep(SLEEP_MOVIMIENTO);
            } catch (InterruptedException e) {
                return;
            }
        }
        this.enemigo.setActivo(false);
    }
}
