package models;

// nave del jugador. cada modelo concreto (ExploradorModel, CazaEstelarModel, AcorazadoModel) fija su propia
// velocidad y sus propios tiempos de sleep, que son los que despues usan los hilos de movimiento y disparo
public abstract class NaveModel extends ObjetoJuegoModel {

    // Estos valores representan dos ritmos diferentes del juego:
    // una pausa menor implica movimiento o disparos mas frecuentes.
    protected int velocidad;
    protected long sleepMovimiento;
    protected long sleepDisparo;
    protected String nombreModelo;

    public NaveModel(int x, int y, int ancho, int alto, int velocidad, long sleepMovimiento, long sleepDisparo, String nombreModelo) {
        super(x, y, ancho, alto);
        this.velocidad = velocidad;
        this.sleepMovimiento = sleepMovimiento;
        this.sleepDisparo = sleepDisparo;
        this.nombreModelo = nombreModelo;
    }

    // la nave del jugador se mueve por teclado, no sola, asi que este metodo se deja vacio
    // solo existe para cumplir el contrato de ObjetoJuegoModel
    @Override
    public void mover() {
        // La nave se mueve mediante eventos del teclado, no mediante un hilo.
    }

    public void moverArriba() {
        this.y -= this.velocidad;
    }

    public void moverAbajo() {
        this.y += this.velocidad;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public long getSleepMovimiento() {
        return sleepMovimiento;
    }

    public long getSleepDisparo() {
        return sleepDisparo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }
}
