package views;

import template.TemplateView;
import controllers.BancoPilotosController;
import javax.swing.*;

public class MenuView extends TemplateView {

    private JPanel panel;
    private JButton btnJugar;
    private JButton btnCrearPiloto;
    private JButton btnTopPuntajes;
    private JButton btnSalir;

    private BancoPilotosController bancoPilotos;

    public MenuView() {
        super("Menu principal");

        this.bancoPilotos = new BancoPilotosController();
        this.crearPantalla();
    }

    @Override
    public void crearPantalla() {
        this.setLayout(null);
        this.setBounds(0, 0, this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);

        this.btnJugar = new JButton("Jugar");
        this.btnJugar.setBounds(150, 100, 180, 35);
        this.btnJugar.addActionListener(e -> this.irAJugar());

        this.btnCrearPiloto = new JButton("Crear piloto");
        this.btnCrearPiloto.setBounds(150, 150, 180, 35);
        this.btnCrearPiloto.addActionListener(e -> new CrearPilotoView(this.bancoPilotos).setVisible(true));

        this.btnTopPuntajes = new JButton("Top de puntajes");
        this.btnTopPuntajes.setBounds(150, 200, 180, 35);
        this.btnTopPuntajes.addActionListener(e -> new TopPuntajesView(this.bancoPilotos).setVisible(true));

        this.btnSalir = new JButton("Salir");
        this.btnSalir.setBounds(150, 250, 180, 35);
        this.btnSalir.addActionListener(e -> {
            this.CerrarVentana();
            System.exit(0);
        });

        this.panel.add(this.btnJugar);
        this.panel.add(this.btnCrearPiloto);
        this.panel.add(this.btnTopPuntajes);
        this.panel.add(this.btnSalir);

        this.add(this.panel);
    }

    private void irAJugar() {
        if (this.bancoPilotos.getCantidad() == 0) {
            JOptionPane.showMessageDialog(this, "Primero crea un piloto");
            return;
        }
        new SeleccionPilotoView(this.bancoPilotos).setVisible(true);
    }
}
