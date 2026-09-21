package models;

import java.awt.Color;
import java.awt.Graphics;

// dificultad dificil: nave pesada y lenta, dificil de esquivar, pero dispara en rafagas cada 0.3 segundos
public class AcorazadoModel extends NaveModel {

    public AcorazadoModel(int x, int y) {
        super(x, y, 50, 30, 2, 40L, 300L, "Acorazado");
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.ancho, this.alto);
    }
}
