package POO.Interfaces;

import javax.swing.JFrame;

/**
 *
 * @author Diegomrza
 */
public abstract class VentanaBase extends JFrame {
    
    public static final int ANCHO_DEFAULT = 600;
    public static final int ALTO_DEFAULT = 400;
    
    public VentanaBase(String titulo) {
        // Llamada al constructor padre (JFrame)
        super(titulo);
        
        this.setSize(ANCHO_DEFAULT, ALTO_DEFAULT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    // Cada ventana hija debe construir su propio contenido
    public abstract void crearPantalla();
    
   
}
