package controllers;

import models.EnemigoModel;

// mismo criterio que ProyectilesVectorController pero para los enemigos en pantalla
public class EnemigosVectorController {

    // El generador y el Timer de Swing pueden acceder a este arreglo desde hilos
    // distintos; cantidad indica cuantas posiciones son validas.
    private EnemigoModel[] enemigos;
    private int cantidad;

    public EnemigosVectorController() {
        this.enemigos = new EnemigoModel[10];
        this.cantidad = 0;
    }

    public synchronized void agregar(EnemigoModel enemigo) {
        if (this.cantidad == this.enemigos.length) {
            this.redimensionar();
        }
        this.enemigos[this.cantidad] = enemigo;
        this.cantidad++;
    }

    private void redimensionar() {
        // Las posiciones posteriores a cantidad no forman parte de la coleccion logica.
        EnemigoModel[] nuevo = new EnemigoModel[this.enemigos.length * 2];
        for (int i = 0; i < this.enemigos.length; i++) {
            nuevo[i] = this.enemigos[i];
        }
        this.enemigos = nuevo;
    }

    public synchronized EnemigoModel obtener(int indice) {
        return this.enemigos[indice];
    }

    public synchronized int getCantidad() {
        return this.cantidad;
    }

    public synchronized void limpiarInactivos() {
        // Compactar evita que la cantidad de enemigos crezca indefinidamente,
        // aunque los objetos que salieron de pantalla permanezcan en memoria.
        EnemigoModel[] nuevo = new EnemigoModel[this.enemigos.length];
        int nuevaCantidad = 0;

        for (int i = 0; i < this.cantidad; i++) {
            if (this.enemigos[i].isActivo()) {
                nuevo[nuevaCantidad] = this.enemigos[i];
                nuevaCantidad++;
            }
        }

        this.enemigos = nuevo;
        this.cantidad = nuevaCantidad;
    }
}
