package models;

import java.awt.Color;
import java.awt.Graphics;

// proyectil disparado por el jugador, viaja hacia X maximo
public class ProyectilModel extends ObjetoJuegoModel {

    private int velocidad;

    public ProyectilModel(int x, int y) {
        super(x, y, 10, 4);
        this.velocidad = 8;
    }

    @Override
    public void mover() {
        this.x += this.velocidad;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
    }
}
