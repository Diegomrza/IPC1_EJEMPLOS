package models;

import java.awt.Graphics;

// clase base de todo lo que aparece en el campo de batalla (nave, proyectiles, enemigos, asteroides)
// aqui vive el comportamiento comun: posicion, tamano y deteccion de colision
public abstract class ObjetoJuegoModel {

    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;
    protected boolean activo;

    public ObjetoJuegoModel(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.activo = true;
    }

    // cada objeto decide como se mueve (proyectil hacia la derecha, enemigo hacia la izquierda, etc)
    public abstract void mover();

    // cada objeto decide como se dibuja
    public abstract void dibujar(Graphics g);

    // colision por rectangulos, la comparten todos los objetos del juego
    public boolean colisionaCon(ObjetoJuegoModel otro) {
        // La colision se calcula con los rectangulos ocupados por ambos objetos.
        // Es suficiente para esta practica y evita depender de imagenes o formas.
        if (!this.activo || !otro.activo) {
            return false;
        }
        return this.x < otro.x + otro.ancho
                && this.x + this.ancho > otro.x
                && this.y < otro.y + otro.alto
                && this.y + this.alto > otro.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
