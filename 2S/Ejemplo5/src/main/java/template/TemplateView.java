package template;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class TemplateView extends JFrame {
    
    private String titulo;
    protected int DEFAULT_WIDTH = 500;
    protected int DEFAULT_HEIGHT = 500;
    
    public TemplateView(String titulo) {
        super(titulo);
        this.titulo = titulo;
    }
    
    public abstract void crearPantalla();

    // centra la ventana en pantalla cada vez que se muestra, para no repetir esto en cada vista
    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            this.setLocationRelativeTo(null);
        }
        super.setVisible(visible);
    }

    public void CerrarVentana() {
        JOptionPane.showMessageDialog(this, "Cerrando ventana " + this.titulo);
        this.dispose();
    }
}
