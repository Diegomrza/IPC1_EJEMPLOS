package views;

import template.TemplateView;
import controllers.BancoPilotosController;
import models.PilotoModel;
import models.NaveModel;
import models.ExploradorModel;
import models.CazaEstelarModel;
import models.AcorazadoModel;

import javax.swing.*;

public class CrearPilotoView extends TemplateView {

    private BancoPilotosController bancoPilotos;

    private JPanel panel;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblNave;
    private JRadioButton rbExplorador;
    private JRadioButton rbCazaEstelar;
    private JRadioButton rbAcorazado;
    private ButtonGroup grupoNaves;
    private JButton btnCrear;
    private JButton btnVolver;

    public CrearPilotoView(BancoPilotosController bancoPilotos) {
        super("Crear piloto");
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

        this.lblNombre = new JLabel("Nombre del piloto:");
        this.lblNombre.setBounds(20, 20, 200, 25);

        this.txtNombre = new JTextField();
        this.txtNombre.setBounds(20, 45, 250, 25);

        this.lblNave = new JLabel("Modelo de nave:");
        this.lblNave.setBounds(20, 85, 200, 25);

        this.rbExplorador = new JRadioButton("Explorador - alta velocidad, disparo cada 2s");
        this.rbExplorador.setBounds(20, 110, 350, 25);

        this.rbCazaEstelar = new JRadioButton("Caza Estelar - velocidad media, disparo cada 1s");
        this.rbCazaEstelar.setBounds(20, 140, 350, 25);

        this.rbAcorazado = new JRadioButton("Acorazado - lenta, rafagas cada 0.3s");
        this.rbAcorazado.setBounds(20, 170, 350, 25);

        this.grupoNaves = new ButtonGroup();
        this.grupoNaves.add(this.rbExplorador);
        this.grupoNaves.add(this.rbCazaEstelar);
        this.grupoNaves.add(this.rbAcorazado);

        this.btnCrear = new JButton("Crear piloto");
        this.btnCrear.setBounds(20, 220, 150, 30);
        this.btnCrear.addActionListener(e -> this.crearPiloto());

        this.btnVolver = new JButton("Volver");
        this.btnVolver.setBounds(180, 220, 100, 30);
        this.btnVolver.addActionListener(e -> this.dispose());

        this.panel.add(this.lblNombre);
        this.panel.add(this.txtNombre);
        this.panel.add(this.lblNave);
        this.panel.add(this.rbExplorador);
        this.panel.add(this.rbCazaEstelar);
        this.panel.add(this.rbAcorazado);
        this.panel.add(this.btnCrear);
        this.panel.add(this.btnVolver);

        this.add(this.panel);
    }

    private void crearPiloto() {
        String nombre = this.txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio");
            return;
        }

        if (this.bancoPilotos.buscarPorNombre(nombre) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un piloto con ese nombre");
            return;
        }

        NaveModel nave = this.crearNaveSeleccionada();

        if (nave == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un modelo de nave");
            return;
        }

        PilotoModel piloto = new PilotoModel(nombre, nave);
        this.bancoPilotos.agregar(piloto);

        JOptionPane.showMessageDialog(this, "Piloto " + nombre + " creado con nave " + nave.getNombreModelo());
        this.txtNombre.setText("");
        this.grupoNaves.clearSelection();
    }

    // la posicion inicial de la nave se define aca, lo demas (velocidad, sleeps) ya viene fijo desde su propia clase
    private NaveModel crearNaveSeleccionada() {
        if (this.rbExplorador.isSelected()) {
            return new ExploradorModel(20, 220);
        }
        if (this.rbCazaEstelar.isSelected()) {
            return new CazaEstelarModel(20, 220);
        }
        if (this.rbAcorazado.isSelected()) {
            return new AcorazadoModel(20, 220);
        }
        return null;
    }
}
