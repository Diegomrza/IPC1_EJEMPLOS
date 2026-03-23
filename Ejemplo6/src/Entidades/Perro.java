package Entidades;

public class Perro extends Animal {

    public Perro(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " dice: Guau Guau");
    }
    
}