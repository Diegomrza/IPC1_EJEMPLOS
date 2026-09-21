package views;

import template.TemplateView;
import models.PilotoModel;
import models.NaveModel;
import models.ProyectilModel;
import models.EnemigoModel;
import controllers.ProyectilesVectorController;
import controllers.EnemigosVectorController;
import controllers.ProyectilHiloController;
import controllers.GeneradorEnemigosController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// pantalla de la partida. cada proyectil y cada enemigo vive en su propio hilo (ver controllers),
// esta clase solo dibuja el estado actual y revisa colisiones en cada tick de repintado
public class JuegoView extends TemplateView {

    private PilotoModel piloto;
    private NaveModel nave;

    private ProyectilesVectorController proyectiles;
    private EnemigosVectorController enemigos;
    private GeneradorEnemigosController generador;

    private JPanel panel;
    private JPanel lienzo;
    private JLabel lblPuntaje;

    private Timer timerRepintado;
    private int puntaje;
    private long ultimoDisparo;
    private boolean jugando;

    // constructor recibe el piloto que va a jugar, y de ahi obtiene la nave y el puntaje maximo
    public JuegoView(PilotoModel piloto) {
        super("Partida - " + piloto.getNombre());
        this.piloto = piloto;
        this.nave = piloto.getNave();
        this.DEFAULT_WIDTH = 700;
        this.DEFAULT_HEIGHT = 500;
        this.crearPantalla();
    }

    @Override
    public void crearPantalla() {
        this.setLayout(null);
        this.setBounds(0, 0, this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);

        this.lblPuntaje = new JLabel("Puntaje: 0");
        this.lblPuntaje.setBounds(10, 5, 200, 20);

        this.lienzo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                JuegoView.this.dibujarEscena(g);
            }
        };
        this.lienzo.setBackground(Color.BLACK);
        this.lienzo.setBounds(0, 25, this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT - 25);
        this.lienzo.setFocusable(true);

        this.panel.add(this.lblPuntaje);
        this.panel.add(this.lienzo);
        this.add(this.panel);

        this.iniciarPartida();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // el foco solo se puede pedir una vez la ventana ya esta visible
                JuegoView.this.lienzo.requestFocusInWindow();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                JuegoView.this.detenerPartida();
            }
        });
    }

    private void iniciarPartida() {
        this.proyectiles = new ProyectilesVectorController();
        this.enemigos = new EnemigosVectorController();
        this.puntaje = 0;
        this.ultimoDisparo = 0L;
        this.jugando = true;

        this.generador = new GeneradorEnemigosController(this.enemigos, this.lienzo.getWidth() > 0 ? this.lienzo.getWidth() : this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT - 25);
        this.generador.start();

        this.lienzo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                JuegoView.this.manejarTecla(e.getKeyCode());
            }
        });

        // El Timer corre en el hilo de eventos de Swing. Consulta el estado,
        // revisa colisiones y solicita el repintado; no mueve los objetos.
        this.timerRepintado = new Timer(16, e -> {
            this.revisarColisiones();
            this.lienzo.repaint();
        });
        this.timerRepintado.start();
    }

    private void manejarTecla(int codigo) {
        if (!this.jugando) {
            return;
        }

        if (codigo == KeyEvent.VK_UP) {
            this.nave.moverArriba();
            this.limitarNaveAlLienzo();
        } else if (codigo == KeyEvent.VK_DOWN) {
            this.nave.moverAbajo();
            this.limitarNaveAlLienzo();
        } else if (codigo == KeyEvent.VK_SPACE) {
            this.disparar();
        }
    }

    // limita la nave al lienzo, para que no se salga de la pantalla
    private void limitarNaveAlLienzo() {
        int alturaLienzo = this.lienzo.getHeight();
        if (this.nave.getY() < 0) {
            this.nave.setY(0);
        } else if (this.nave.getY() + this.nave.getAlto() > alturaLienzo) {
            this.nave.setY(alturaLienzo - this.nave.getAlto());
        }
    }

    // respeta el sleepDisparo de la nave, esto es lo que hace que las tres dificultades se sientan distintas
    private void disparar() {
        long ahora = System.currentTimeMillis();
        if (ahora - this.ultimoDisparo < this.nave.getSleepDisparo()) {
            return;
        }
        this.ultimoDisparo = ahora;

        int x = this.nave.getX() + this.nave.getAncho();
        int y = this.nave.getY() + (this.nave.getAlto() / 2);
        ProyectilModel proyectil = new ProyectilModel(x, y);
        this.proyectiles.agregar(proyectil);

        ProyectilHiloController hiloProyectil = new ProyectilHiloController(proyectil, this.nave.getSleepMovimiento(), this.lienzo.getWidth());
        hiloProyectil.start();
    }

    // revisa colisiones entre proyectiles y enemigos, y entre enemigos y la nave. si hay colision, se desactiva el objeto correspondiente
    private void revisarColisiones() {
        if (!this.jugando) {
            return;
        }

        // Al desactivar ambos objetos, los hilos de movimiento encuentran false
        // en su siguiente iteracion y terminan sin tener que destruir el hilo
        // desde la interfaz.
        for (int i = 0; i < this.proyectiles.getCantidad(); i++) {
            ProyectilModel proyectil = this.proyectiles.obtener(i);
            if (!proyectil.isActivo()) {
                continue;
            }
            for (int j = 0; j < this.enemigos.getCantidad(); j++) {
                EnemigoModel enemigo = this.enemigos.obtener(j);
                if (enemigo.isActivo() && proyectil.colisionaCon(enemigo)) {
                    proyectil.setActivo(false);
                    enemigo.setActivo(false);
                    this.puntaje += 10;
                    this.lblPuntaje.setText("Puntaje: " + this.puntaje);
                    break;
                }
            }
        }

        for (int j = 0; j < this.enemigos.getCantidad(); j++) {
            EnemigoModel enemigo = this.enemigos.obtener(j);
            if (enemigo.isActivo() && this.nave.colisionaCon(enemigo)) {
                this.terminarPartidaPorDerrota();
                return;
            }
        }

        this.proyectiles.limpiarInactivos();
        this.enemigos.limpiarInactivos();
    }

    // termina la partida, actualiza el puntaje maximo del piloto y muestra un mensaje con el puntaje final
    private void terminarPartidaPorDerrota() {
        this.detenerPartida();
        this.piloto.actualizarPuntajeMaximo(this.puntaje);
        JOptionPane.showMessageDialog(this, "Nave destruida. Puntaje final: " + this.puntaje);
        this.dispose();
    }

    // apaga el generador de enemigos y el timer; los hilos de proyectiles y enemigos que sigan
    // vivos se apagan solos porque revisan isActivo() en cada vuelta de su ciclo
    private void detenerPartida() {
        this.jugando = false;

        if (this.timerRepintado != null) {
            this.timerRepintado.stop();
        }
        if (this.generador != null) {
            // detener() cambia la bandera y despierta al generador si estaba
            // bloqueado dentro de Thread.sleep().
            this.generador.detener();
        }
    }

    // dibuja la nave, los proyectiles y los enemigos en el lienzo
    private void dibujarEscena(Graphics g) {
        this.nave.dibujar(g);

        for (int i = 0; i < this.proyectiles.getCantidad(); i++) {
            ProyectilModel proyectil = this.proyectiles.obtener(i);
            if (proyectil.isActivo()) {
                proyectil.dibujar(g);
            }
        }

        for (int i = 0; i < this.enemigos.getCantidad(); i++) {
            EnemigoModel enemigo = this.enemigos.obtener(i);
            if (enemigo.isActivo()) {
                enemigo.dibujar(g);
            }
        }
    }
}
