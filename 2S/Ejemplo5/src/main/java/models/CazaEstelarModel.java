package models;

import java.awt.Color;
import java.awt.Graphics;

// dificultad normal: equilibrio entre velocidad de movimiento y cadencia de disparo (cada 1 segundo)
public class CazaEstelarModel extends NaveModel {

    public CazaEstelarModel(int x, int y) {
        super(x, y, 40, 20, 4, 25L, 1000L, "Caza Estelar");
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
    }
}
