package ejemplo6;

import Entidades.Animal;
import Entidades.Gato;
import Entidades.Perro;

public class Ejemplo6 {

    public static void main(String[] args) {
        
        Animal animal1 = new Perro("Firulais");
        Animal animal2 = new Gato("Michi");

        animal1.hacerSonido();
        animal2.hacerSonido();
    }
}
