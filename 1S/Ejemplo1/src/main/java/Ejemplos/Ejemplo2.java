package Ejemplos;

import java.util.Scanner;

/**
 * @author Diego Robles
 */
public class Ejemplo2 {

    public static int filas = 0; // filas del tablero actual
    public static int columnas = 0; // columnas del tablero actual
    public static int contador = 1;
    public static int[][] tablero;

    public static void main(String[] args) {
        // Variables();
        // Arreglos();

        int opcion;
        int limite_inferior = 1;
        int limite_superior = 5;

        String mensaje = "Ingrese la opcion: ";
        String mensajeError = "Debe ingresar una opcion entre " + limite_inferior + " y " + limite_superior;

        do {
            System.out.println("============== Menu ==============");
            System.out.println("1. Jugar");
            System.out.println("2. Mostrar punteos");
            System.out.println("3. Salir");

            opcion = EntradaNumeroConMensajes(1, 5, mensaje, mensajeError);

            switch (opcion) {
                case 1:
                    Tablero();
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        } while (opcion != 5);
    }
    
    // solicita datos al usuario para creacion del tablero
    public static void Tablero() {
        // Mientras que la opcion ingresada sea invalida (-1) seguir solicitando el dato al usuario
        do {
            filas = EntradaNumeroConMensajes(5, 10, "Ingrese la cantidad de filas: ", "El numero debe estar entre " + 5 + " y " + 10);
        } while (filas == -1);
        
        // Mientras que la opcion ingresada sea invalida (-1) seguir solicitando el dato al usuario
        do {
            columnas = EntradaNumeroConMensajes(5, 10, "Ingrese la cantidad de columnas: ", "El numero debe estar entre " + 5 + " y " + 10);
        } while (columnas == -1);

        // Creacion del arreglo con dimensiones seleccionadas
        int matriz[][] = new int[filas][columnas];

        // Imprimiendo el tablero
        ImprimirMatriz(matriz, false);
    }

    // Imprimer el tablero
    public static void ImprimirMatriz(int[][] matriz, boolean salto) {
        System.out.println("=================");
        
        for (int i = matriz.length; i > 0; i--) { // Filas
            
            for (int j = 0; j < matriz[i].length; j++) { // Columnas
                if (salto) {
                    System.out.println(matriz[i][j]);
                } else {
                    System.out.print(matriz[i][j]);
                }
            }
            System.out.println("");
        }
        
        System.out.println("=================");
    }

    /*
     * Descripcion:
     * Funcion personalizada que permite obtener un numero por consola
     * Recibe:
     * Limite inferior (int)
     * Limite superior (int)
     * Mensaje que se mostrará al usuario (String)
     * Mensaje en caso de error para mostrar al usuario (String)
     * Retorna:
     * Opcion valida seleccionada (int)
     */
    public static int EntradaNumeroConMensajes(int limite_inferior, int limite_superior, String mensaje, String mensajeError) {
        // Para entrada de datos
        Scanner sc = new Scanner(System.in);

        // Variables a utilizar
        int opcion;

        // Mostrando mensaje al usuario
        System.out.print(mensaje);

        try {
            // capturando la entrada del usuario
            opcion = sc.nextInt();
            if (opcion < limite_inferior || opcion > limite_superior)
                throw new Exception(mensajeError);
        } catch (Exception ex) {
            // En caso de error mostrar mensaje al usuario
            System.out.println(mensajeError);
            // Y estableciendo valor por defecto a variable
            opcion = -1;
        }
        // No cerramos el Scanner para evitar cerrar System.in

        return opcion;
    }

}
