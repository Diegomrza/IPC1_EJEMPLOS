package controllers;

import models.ProyectilModel;

// vector propio de proyectiles activos en pantalla, se accede desde el hilo de cada proyectil
// y desde el hilo de repintado a la vez, por eso los metodos son synchronized
public class ProyectilesVectorController {

    // Solo los primeros cantidad elementos son proyectiles validos. Los metodos
    // synchronized protegen la estructura cuando se agrega desde la interfaz
    // mientras el Timer la recorre para dibujar y revisar colisiones.
    private ProyectilModel[] proyectiles;
    private int cantidad;

    public ProyectilesVectorController() {
        this.proyectiles = new ProyectilModel[10];
        this.cantidad = 0;
    }

    public synchronized void agregar(ProyectilModel proyectil) {
        if (this.cantidad == this.proyectiles.length) {
            this.redimensionar();
        }
        this.proyectiles[this.cantidad] = proyectil;
        this.cantidad++;
    }

    private void redimensionar() {
        // Se amplia la capacidad copiando el contenido activo del arreglo actual.
        ProyectilModel[] nuevo = new ProyectilModel[this.proyectiles.length * 2];
        for (int i = 0; i < this.proyectiles.length; i++) {
            nuevo[i] = this.proyectiles[i];
        }
        this.proyectiles = nuevo;
    }

    public synchronized ProyectilModel obtener(int indice) {
        return this.proyectiles[indice];
    }

    public synchronized int getCantidad() {
        return this.cantidad;
    }

    // reconstruye el arreglo dejando solo los proyectiles todavia activos, para que no crezca sin control
    public synchronized void limpiarInactivos() {
        // Los proyectiles desactivados se eliminan logicamente al compactar el arreglo.
        ProyectilModel[] nuevo = new ProyectilModel[this.proyectiles.length];
        int nuevaCantidad = 0;

        for (int i = 0; i < this.cantidad; i++) {
            if (this.proyectiles[i].isActivo()) {
                nuevo[nuevaCantidad] = this.proyectiles[i];
                nuevaCantidad++;
            }
        }

        this.proyectiles = nuevo;
        this.cantidad = nuevaCantidad;
    }
}
