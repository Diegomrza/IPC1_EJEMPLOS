package controllers;

import models.ProyectilModel;

// hilo independiente por cada proyectil disparado. se mueve solo hasta salir de la pantalla
// o hasta que una colision lo marque como inactivo
public class ProyectilHiloController extends Thread {

    private ProyectilModel proyectil;
    private long sleepMovimiento;
    private int anchoPantalla;

    public ProyectilHiloController(ProyectilModel proyectil, long sleepMovimiento, int anchoPantalla) {
        this.proyectil = proyectil;
        this.sleepMovimiento = sleepMovimiento;
        this.anchoPantalla = anchoPantalla;
    }

    @Override
    public void run() {
        // Cada proyectil tiene su propio ciclo de movimiento. Se detiene cuando
        // sale del lienzo o cuando una colision lo desactiva.
        while (this.proyectil.isActivo() && this.proyectil.getX() < this.anchoPantalla) {
            this.proyectil.mover();
            try {
                // El tiempo recibido depende del modelo de nave seleccionado.
                Thread.sleep(this.sleepMovimiento);
            } catch (InterruptedException e) {
                return;
            }
        }
        this.proyectil.setActivo(false);
    }
}
