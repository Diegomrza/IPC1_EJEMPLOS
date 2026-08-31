package POO;

import javax.swing.JOptionPane;
import models.PersonaModel;
import views.LoginView;

/**
 *
 * @author Diegomrza
 */
public class POO {

    public static void main(String[] args) {
        
//        JOptionPane.showMessageDialog(null, "Hola mundo");
//        JOptionPane.showMessageDialog(null, "Hola mundo");
//        JOptionPane.showMessageDialog(null, "Hola mundo");
//        JOptionPane.showMessageDialog(null, "Hola mundo");
        
        PersonaModel persona = new PersonaModel();
        persona.DesasignarmeIPC1();
        
        LoginView ventanaInicio = new LoginView();
        ventanaInicio.setVisible(true);
    }
}
