package views;

import controllers.AutenticacionController;
import POO.Interfaces.BaseInterface;
import POO.Interfaces.VentanaBase;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Diegomrza
 */

public class LoginView extends VentanaBase implements BaseInterface {
    
    private AutenticacionController controller;
    
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    
    private final int WIDTH = 350;
    private final int HEIGHT = 250;
    
    public LoginView() {
        super("Centro de Rescate Animal - Login");
        this.setSize(this.WIDTH, this.HEIGHT);
        this.crearPantalla();
    }
    
    public void crearPantalla() {
        this.panelPrincipal = new JPanel();
        
        this.panelPrincipal.setLayout(null);
        this.panelPrincipal.setBackground(Color.WHITE);
        this.panelPrincipal.setBounds(0, 0, this.WIDTH, this.HEIGHT); // debe cubrir el tamaño del frame

        JLabel lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setBounds(30, 30, 80, 25);
        this.panelPrincipal.add(lblUsuario); // faltaba

        this.txtUsuario = new JTextField();
        this.txtUsuario.setBounds(120, 30, 180, 25);
        this.panelPrincipal.add(this.txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña: "); // faltaba
        lblPassword.setBounds(30, 70, 80, 25);
        this.panelPrincipal.add(lblPassword);

        this.txtPassword = new JPasswordField(); // faltaba inicializar
        this.txtPassword.setBounds(120, 70, 180, 25);
        this.panelPrincipal.add(this.txtPassword);

        this.btnIngresar = new JButton("Ingresar");
        this.btnIngresar.setBounds(120, 110, 100, 30);
        this.panelPrincipal.add(this.btnIngresar);
        
        JButton btnRegistrarse = new JButton("No tienes una cuenta? Registrate!");
        btnRegistrarse.setBounds(40, 150, 250, 25);
        this.panelPrincipal.add(btnRegistrarse);
        
        btnRegistrarse.addActionListener(e -> {
            this.RedireccionInicio();
            RegisterView ventanaRegistro = new RegisterView();
            
            
            ventanaRegistro.setVisible(true);
        });
        
        // Evento de click al boton Ingresar
        this.btnIngresar.addActionListener( (ActionEvent e) -> {
            String usuario = this.txtUsuario.getText();
            String password = new String(this.txtPassword.getPassword());
            
            boolean correcto = this.controller.InicioSesion(usuario, password);
        });
        
        this.add(this.panelPrincipal);
    }
    
    @Override
    public void RedireccionInicio() {
        // Liberar recursos
        this.dispose();
    }
}
