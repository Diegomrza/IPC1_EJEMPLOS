package models;

import java.awt.Color;
import java.awt.Graphics;

// dificultad facil: nave rapida pero con recarga de proyectil lenta (cada 2 segundos)
public class ExploradorModel extends NaveModel {

    public ExploradorModel(int x, int y) {
        super(x, y, 40, 20, 6, 15L, 2000L, "Explorador");
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
    }
}
