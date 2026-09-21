package controllers;

import models.PilotoModel;

// vector propio de pilotos, hecho a mano para no depender de ArrayList.
// cuando se llena, duplica su capacidad copiando los elementos a un arreglo nuevo
public class BancoPilotosController {

    // Solo las posiciones entre 0 y cantidad - 1 contienen pilotos validos.
    // El resto del arreglo representa espacio disponible para nuevos registros.
    private PilotoModel[] pilotos;
    private int cantidad;

    public BancoPilotosController() {
        this.pilotos = new PilotoModel[4];
        this.cantidad = 0;
    }

    public void agregar(PilotoModel piloto) {
        if (this.cantidad == this.pilotos.length) {
            this.redimensionar();
        }
        // cantidad tambien funciona como el indice de la siguiente posicion libre.
        this.pilotos[this.cantidad] = piloto;
        this.cantidad++;
    }

    private void redimensionar() {
        // Se crea un arreglo mas grande y se copian manualmente los registros activos.
        // Esto imita una coleccion dinamica sin utilizar ArrayList.
        PilotoModel[] nuevo = new PilotoModel[this.pilotos.length * 2];
        for (int i = 0; i < this.pilotos.length; i++) {
            nuevo[i] = this.pilotos[i];
        }
        this.pilotos = nuevo;
    }

    public PilotoModel buscarPorNombre(String nombre) {
        for (int i = 0; i < this.cantidad; i++) {
            if (this.pilotos[i].getNombre().equalsIgnoreCase(nombre)) {
                return this.pilotos[i];
            }
        }
        return null;
    }

    public PilotoModel obtener(int indice) {
        if (indice < 0 || indice >= this.cantidad) {
            return null;
        }
        return this.pilotos[indice];
    }

    public int getCantidad() {
        return this.cantidad;
    }
}
