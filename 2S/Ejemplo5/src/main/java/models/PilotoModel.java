package models;

// datos del piloto que se guardan en el banco de pilotos. la nave asignada define
// la dificultad con la que ese piloto va a jugar
public class PilotoModel {

    private String nombre;
    private NaveModel nave;
    private int puntajeMaximo;

    public PilotoModel(String nombre, NaveModel nave) {
        this.nombre = nombre;
        this.nave = nave;
        this.puntajeMaximo = 0;
    }

    public void actualizarPuntajeMaximo(int puntaje) {
        if (puntaje > this.puntajeMaximo) {
            this.puntajeMaximo = puntaje;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public NaveModel getNave() {
        return nave;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }
}
