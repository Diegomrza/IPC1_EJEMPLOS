package views;

import template.TemplateView;
import controllers.BancoPilotosController;
import models.PilotoModel;

import javax.swing.*;

public class SeleccionPilotoView extends TemplateView {

    private BancoPilotosController bancoPilotos;

    private JPanel panel;
    private JList<String> listaPilotos;
    private JButton btnIniciar;
    private JButton btnVolver;

    public SeleccionPilotoView(BancoPilotosController bancoPilotos) {
        super("Seleccionar piloto");
        this.bancoPilotos = bancoPilotos;
        this.crearPantalla();
    }

    @Override
    public void crearPantalla() {
        this.setLayout(null);
        this.setBounds(0, 0, this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);

        String[] nombres = new String[this.bancoPilotos.getCantidad()];
        for (int i = 0; i < nombres.length; i++) {
            PilotoModel piloto = this.bancoPilotos.obtener(i);
            nombres[i] = piloto.getNombre() + " - " + piloto.getNave().getNombreModelo();
        }

        this.listaPilotos = new JList<>(nombres);
        JScrollPane scroll = new JScrollPane(this.listaPilotos);
        scroll.setBounds(20, 20, 300, 200);

        this.btnIniciar = new JButton("Iniciar partida");
        this.btnIniciar.setBounds(20, 240, 150, 30);
        this.btnIniciar.addActionListener(e -> this.iniciarPartida());

        this.btnVolver = new JButton("Volver");
        this.btnVolver.setBounds(180, 240, 100, 30);
        this.btnVolver.addActionListener(e -> this.dispose());

        this.panel.add(scroll);
        this.panel.add(this.btnIniciar);
        this.panel.add(this.btnVolver);

        this.add(this.panel);
    }

    private void iniciarPartida() {
        int indice = this.listaPilotos.getSelectedIndex();

        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un piloto de la lista");
            return;
        }

        PilotoModel piloto = this.bancoPilotos.obtener(indice);

        JuegoView juego = new JuegoView(piloto);
        juego.setVisible(true);
        this.dispose();
    }
}
