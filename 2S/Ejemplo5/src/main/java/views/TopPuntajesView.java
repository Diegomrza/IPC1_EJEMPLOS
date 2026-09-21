package views;

import template.TemplateView;
import controllers.BancoPilotosController;
import models.PilotoModel;

import javax.swing.*;

public class TopPuntajesView extends TemplateView {

    private BancoPilotosController bancoPilotos;

    private JPanel panel;
    private JList<String> listaPuntajes;
    private JButton btnVolver;

    public TopPuntajesView(BancoPilotosController bancoPilotos) {
        super("Top de puntajes");
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

        int cantidad = this.bancoPilotos.getCantidad();
        String[] filas = new String[cantidad == 0 ? 1 : cantidad];

        if (cantidad == 0) {
            filas[0] = "Todavia no hay pilotos registrados";
        } else {
            for (int i = 0; i < cantidad; i++) {
                PilotoModel piloto = this.bancoPilotos.obtener(i);
                filas[i] = piloto.getNombre() + " - " + piloto.getPuntajeMaximo() + " pts";
            }
        }

        this.listaPuntajes = new JList<>(filas);
        JScrollPane scroll = new JScrollPane(this.listaPuntajes);
        scroll.setBounds(20, 20, 300, 200);

        this.btnVolver = new JButton("Volver");
        this.btnVolver.setBounds(20, 240, 100, 30);
        this.btnVolver.addActionListener(e -> this.dispose());

        this.panel.add(scroll);
        this.panel.add(this.btnVolver);

        this.add(this.panel);
    }
}
