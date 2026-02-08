package Ejemplos;

// Scanner permite leer datos ingresados por el usuario desde la consola u otras fuentes
import java.util.Scanner;

/**
 *
 * @author Diego Robles
 */
public class Ejemplo1 {

    // Variable estática: se comparte entre todas las instancias de la clase
    // y puede ser usada directamente por métodos static.
    public static int contador;
    
    // Método principal del programa. Es el punto de entrada de la aplicación.
    // La JVM ejecuta este método primero sin necesidad de crear un objeto.
    // Debe ser static para poder iniciarse directamente desde la clase.
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        // Llamadas a metodos estaticos
        TiposDatos();
        EntradaYSalida();
        Operadores();
        Estructuras();
        Ciclos();
        Arreglos();
    }
    
    // Métodos estáticos: pertenecen a la clase y no a una instancia.
    // Pueden llamarse sin crear objetos y es accesible desde main.
    
    public static void TiposDatos () {
        // Texto
        String nombre = "Diego Robles";
        char caracter = 'a'; // a, b, c, 1, 2, 3, etc...
        
        // Numeros
        byte numero1 = 127;         // -128 a 127
        short numero2 = 32767;      // -32768 a 32767
        int numero3 = 2147483647;   // - 2147483648 a 2147483647
        long numero4 = 9223372036854775807l; // -9223372036854775808 a 9223372036854775807
        float numero5 = 1.0f;              // +/- 1.......
        double numero6 = 2.18;             // +/- 1.................
        
        System.out.println("");
        
        System.out.println(caracter);
        System.out.println(nombre);
        System.out.println(numero1);
        System.out.println(numero2);
        System.out.println(numero3);
        System.out.println(numero4);
        System.out.println(numero5);
        System.out.println(numero6); 
        
        Variables();
    }
    
    public static void Variables() {
        String nombre = "Diego Robles";
        short edad = 99;
        double dinero = 200.50;
        boolean graduado = false;
        int telefono = 12345678;
        long dpi = 12345678912345L;

        // Imprimiendo el nombre
        System.out.println("Mi nombre es: " + nombre);

        // Imprimiendo la edad
        System.out.println("Mi edad es: " + String.valueOf(edad));

        // Imprimiendo el dinero
        System.out.println("Tengo " + String.valueOf(dinero) + " quetzales");

        // Condicional para imprimir si estoy graduado o no
        if (graduado)
            System.out.println("Estoy graduado");
        else
            System.out.println("No estoy graduado");

        // Imprimir numero de telefono
        System.out.println("Mi numero de telefono es: " + String.valueOf(telefono));

        // Imprimir numero de dpi
        System.out.println("Mi numero de dpi es: " + String.valueOf(dpi));
    }
    
    public static void EntradaYSalida() {
        /*
        * Se instancia Scanner usando System.in para capturar datos ingresados
        * por el usuario desde la consola.
        */
        Scanner sc = new Scanner(System.in);
                
        /*
        * Métodos comunes de Scanner:
        * nextInt()    -> lee un entero
        * nextDouble() -> lee un decimal
        * nextLine()   -> lee una línea completa de texto
        */
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        
        System.out.println("Ingrese su edad: ");
        int edad = sc.nextInt();
        
        float numero = sc.nextFloat();
        
        System.out.println("Ingrese cuanto dinero quiere gaster: ");
        double dinero = sc.nextDouble();
        
        System.out.println("Hola " + nombre + ", tienes " + edad + " años, quieres gastar " + dinero + " quetzales y tu numero es: " + numero);

        // Se cierra el Scanner para liberar el recurso de entrada
        sc.close();
    }
    
    public static void Operadores() {
        // Aritmeticos
        int numero1 = 10;
        int numero2 = 20;
        
        int suma = numero1 + numero2;
        System.out.println("La suma es: " + suma);
        int resta = numero1 - numero2;
        System.out.println("La resta es: " + resta);
        int multiplicacion = numero1 * numero2;
        System.out.println("La multiplicacion es: " + multiplicacion);
        
        if (numero2 > 0) {
            int division = numero1 / numero2;
            System.out.println("La division es: " + division);
        }else {
            System.out.println("No esta definida la division entre 0");
        }
        
        // Relacionales
        boolean bandera1 = numero1 == numero2; // Igual
        boolean bandera2 = numero1 != numero2; // Diferente
        boolean bandera3 = numero1 > numero2;  // Mayor que
        boolean bandera4 = numero1 >= numero2; // Mayor igual que
        boolean bandera5 = numero1 < numero2;  // Menor que
        boolean bandera6 = numero1 <= numero2; // Menor igual que

        System.out.println("bandera1: " + bandera1);
        System.out.println("bandera2: " + bandera2);
        System.out.println("bandera3: " + bandera3);
        System.out.println("bandera4: " + bandera4);
        System.out.println("bandera5: " + bandera5);
        System.out.println("bandera6: " + bandera6);
        
        // Logicos
        boolean res = (numero1 > 10 && numero2 > 20);  // And
        boolean res2 = (numero1 > 10 || numero2 > 20); // Or

        System.out.println("res: " + res);
        System.out.println("res2: " + res2);
    }
    
    public static void Estructuras() {
        boolean bandera = true;
        int entero = 10;
        
        // if, else, else if
        if (bandera) {
            // Instrucciones
        }
        
        if (entero == 10) {
            // Instrucciones
        } else {
            // Instrucciones
        }
        
        if (entero == 10 && bandera == true) {
            // Instrucciones
        } else if (entero == 20 || bandera) {
            // Instrucciones
        }

        switch (entero) {
            case 10:
                // Instrucciones
                //break;
            case 20:
                // Instrucciones
                //break;
            default:
                // Instrucciones
                //break;
        }
    }
    
    public static void Ciclos() {
        // break, continue, return
        //
        // break: Finaliza inmediatamente la ejecución del bucle (for, while, do-while) o del switch en el que se encuentra y sale de él.
        // continue: Interrumpe la iteración actual del bucle y pasa directamente a la siguiente iteración, sin ejecutar el código restante del ciclo en esa vuelta.
        // return: Finaliza la ejecución de un método. Puede devolver un valor (si el método no es void) o simplemente salir del método.
        
        // for
        for (int i = 0; i < 20; i++) {
            // Instrucciones
            break;
        }
        
//        for (int i: lista) {
//            
//        }
        
        // while
        while (true) {
            System.out.println("// Instrucciones");
            break;
        }
        
        // do while
        do {
            System.out.println("// Instrucciones");
            break;
        } while (true);            
    }
    
    public static void Arreglos() {
        /*
         * 1. Empiezan en el indice 0
         * 2. Deben ser todos del mismo tipo (int)
         * 3. Valor por defecto del tipo de dato (0)
         * 4. Indices terminan en n - 1
         */

        // Creando un arreglo de "n" numeros enteros, donde "n" = 10
        int[] numeros = new int[10];

        // Imprimiendo el valor del arreglo en la posicion 0
        System.out.println("Valor en la posicion [0]: " + numeros[0]);

        // Asignando/Guardando un valor en el arreglo en la posicion 0
        numeros[0] = 20;

        // Imprimiendo el valor del arreglo en la posicion 0
        System.out.println("Valor en la posicion [0]: " + numeros[0] + "\n");
        
        // Imprimiendo un arreglo de enteros
        ImprimirArreglo(numeros, true);

        // Creando arreglo de n strings (cadenas), donde n = 5
        String[] nombres = new String[5];

        // Asignando valores en las posiciones 0 y 1 del arreglo de cadenas
        nombres[0] = "Carlos"; // n = 0
        nombres[1] = "Ana"; // n = 1
        nombres[2] = "Diego"; // n = 2
        nombres[3] = "Pedro"; // n = 3
        nombres[4] = "Ernesto"; // n = 4

        for (int i = 0; i < nombres.length; i++) {
            System.out.println("Nombre en la posicion[" + i + "]: " + nombres[i]);
        }
    }
    
    public static void ImprimirArreglo(int[] arreglo, boolean salto) {
        for (int i = 0; i < arreglo.length; i++) {
            if (salto) {
                System.out.println(arreglo[i]);
            } else {
                System.out.print(arreglo[i]);
            }
        }
    }
}
