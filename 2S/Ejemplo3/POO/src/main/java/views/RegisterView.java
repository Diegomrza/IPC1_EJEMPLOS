package views;

import POO.Interfaces.BaseInterface;
import POO.Interfaces.VentanaBase;
import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Diegomrza
 */
public class RegisterView extends VentanaBase implements BaseInterface {
    
    JPanel panelPrincipal;
    
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtEdad;
    private JTextField txtCarnet;
    
    private final int WIDTH = 350;
    private final int HEIGHT = 250;
    
    public RegisterView() {
        super("Ventana de registro");
    }
    
    @Override
    public void crearPantalla() {
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(null);
        this.panelPrincipal.setBackground(Color.WHITE);
        this.panelPrincipal.setBounds(0, 0, this.WIDTH, this.HEIGHT); // debe cubrir el tamaño del frame
        
        
        this.add(this.panelPrincipal);
    }
    
    public void RedireccionInicio() {
        // Liberar recursos
        this.dispose();
        
        // Creando la ventana de inicio
        LoginView vistaInicio = new LoginView();
        
        // Mostrando la ventana de inicio
        vistaInicio.setVisible(true);
    }
}
