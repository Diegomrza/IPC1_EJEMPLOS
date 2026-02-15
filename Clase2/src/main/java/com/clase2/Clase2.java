package com.clase2;

import java.util.Scanner;

/**
 * Ejemplo simplificado de un juego tipo Pac-Man
 * Muestra: 
 * - Como crear y mostrar un tablero
 * - Como manejar simbolos en el tablero
 * - Movimiento basico del jugador
 * @author Diego Robles
 */
public class Clase2 {

    static int[][] Tablero;     // Tablero de juego actual
    static int PosicionX = -1;  // Posicion horizontal del jugador (fila)
    static int PosicionY = -1;  // Posicion vertical del jugador (columna)
    static int Vidas = 3;       // Vidas del jugador
    static int Punteo = 0;      // Punteo actual del jugador
    static String Usuario = ""; // Nombre del jugador actual
    
    public static void main(String[] args) {
        System.out.println("===== EJEMPLO DE TABLERO Y MOVIMIENTO =====\n");
        
        // Paso 1: Crear tablero de ejemplo (5 filas x 6 columnas)
        CrearTableroEjemplo();
        
        // Paso 2: Mostrar tablero inicial
        System.out.println("Tablero inicial:");
        MostrarTablero();
        
        // Paso 3: Ejecutar algunos movimientos de ejemplo
        System.out.println("\n===== INICIANDO JUEGO =====");
        JuegoSimple();
    }
    
    /**
     * Crea un tablero de ejemplo con elementos predefinidos
     * Simbolos:
     * 0 = Espacio vacio
     * 1 = Fantasma
     * 2 = Premio (moneda)
     * 4 = Pared
     * 5 = PacMan (jugador)
     */
    public static void CrearTableroEjemplo() {
        // Crear tablero de 5 filas x 6 columnas
        Tablero = new int[5][6];
        
        // Llenar todo de espacios vacios (0)
        for (int i = 0; i < Tablero.length; i++) {
            for (int j = 0; j < Tablero[i].length; j++) {
                Tablero[i][j] = 0;
            }
        }
        
        // Colocar algunas paredes (4)
        Tablero[2][2] = 4;
        Tablero[2][3] = 4;
        Tablero[3][4] = 4;
        
        // Colocar algunos premios (2)
        Tablero[0][1] = 2;
        Tablero[1][3] = 2;
        Tablero[3][1] = 2;
        Tablero[4][4] = 2;
        
        // Colocar un fantasma (1)
        Tablero[4][2] = 1;
        
        // Posicion inicial del jugador
        PosicionX = 0; // Fila 0
        PosicionY = 0; // Columna 0
        Tablero[PosicionX][PosicionY] = 5; // Colocar PacMan
    }
    
    /**
     * Muestra el tablero en consola con simbolos visuales
     */
    public static void MostrarTablero() {
        System.out.println("\n+------------+");
        // Recorrer de arriba hacia abajo (de mayor a menor fila)
        for (int i = Tablero.length - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < Tablero[i].length; j++) {
                System.out.print(ObtenerSimbolo(Tablero[i][j]));
            }
            System.out.println("|");
        }
        System.out.println("+------------+");
    }
    
    /**
     * Convierte el numero del tablero a un simbolo visual
     * @param idSimbolo - numero que representa el elemento
     * @return - caracter visual del elemento
     */
    public static String ObtenerSimbolo(int idSimbolo) {
        /*
        0 => Espacio en blanco  => " "
        1 => Fantasma           => "@"
        2 => Premio (moneda)    => "o"
        4 => Pared              => "X"
        5 => PacMan             => "C"
        */
        switch(idSimbolo) {
            case 0:
                return "  "; // Espacio vacio (2 espacios para cuadrar)
            case 1:
                return "@ "; // Fantasma
            case 2:
                return "o "; // Premio
            case 4:
                return "X "; // Pared
            case 5:
                return "C "; // PacMan
            default:
                return "? "; // Desconocido
        }
    }
    
    /**
     * Juego simple con movimientos basicos
     */
    public static void JuegoSimple() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar == true) {
            // Mostrar informacion del juego
            System.out.println("Posicion actual: [" + PosicionX + "][" + PosicionY + "]");
            
            // Pedir movimiento al usuario
            System.out.print("Movimiento (W=arriba, S=abajo, A=izquierda, D=derecha, Q=salir): ");
            String movimiento = sc.nextLine().toUpperCase();
            
            // Procesar movimiento
            switch(movimiento) {
                case "W": // Arriba
                    MoverJugador(PosicionX + 1, PosicionY);
                    break;
                case "S": // Abajo
                    MoverJugador(PosicionX - 1, PosicionY);
                    break;
                case "A": // Izquierda
                    MoverJugador(PosicionX, PosicionY - 1);
                    break;
                case "D": // Derecha
                    MoverJugador(PosicionX, PosicionY + 1);
                    break;
                case "Q": // Salir
                    continuar = false;
                    System.out.println("\nGracias por jugar!");
                    break;
                default:
                    System.out.println("Movimiento invalido. Usa W, A, S, D o Q.");
                    continue;
            }
            
            // Mostrar tablero actualizado
            if (continuar) {
                MostrarTablero();
            }
        }    
    }
    
    /**
     * Mueve al jugador a una nueva posicion
     * @param nuevaX - nueva fila
     * @param nuevaY - nueva columna
     */
    public static void MoverJugador(int nuevaX, int nuevaY) {                
        // TODO: Verificar si esta fuera de los bordes => en su caso si deben poder traspasar bordes y pasar al otro lado del tablero!

        // TODO: En la posicion anterior dejar un espacio vacio, sino iria dejando una "C" en cada posicion que se mueve el jugador
        
        Tablero[PosicionX][PosicionY] = 0; // Colocar jugador
        
        // Le seteamos la posicion al jugador
        PosicionX = nuevaX;
        PosicionY = nuevaY;
        
        // Asignacion del jugador a su nueva posicion
        Tablero[PosicionX][PosicionY] = 5; // Colocar jugador
    }
}
