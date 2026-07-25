package Entidades;

public abstract class Animal {

    // Encapsulamiento
    private String nombre;

    // Constructor
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // Getter
    public String getNombre() {
        return nombre;
    }

    // Método abstracto (cada animal lo implementa diferente)
    public void hacerSonido(){
        
    }
}