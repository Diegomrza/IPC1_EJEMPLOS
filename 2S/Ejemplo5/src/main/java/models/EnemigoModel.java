package models;

import java.awt.Color;
import java.awt.Graphics;

// enemigo generado en el borde derecho de la pantalla, viaja hacia X minimo
public class EnemigoModel extends ObjetoJuegoModel {

    private int velocidad;

    public EnemigoModel(int x, int y) {
        super(x, y, 30, 20);
        this.velocidad = 4;
    }

    @Override
    public void mover() {
        this.x -= this.velocidad;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
    }
}
