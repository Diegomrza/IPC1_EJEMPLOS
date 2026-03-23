package Ejemplo7;

import Algoritmos.BubbleSort;
import Graficas.GraficaBarras;
import Hilos.MiHilo;
import javax.swing.JFrame;

public class Ejemplo7 {

    public static void main(String[] args) {
        Thread hilo = new Thread();
        MiHilo h1 = new MiHilo("Hilo-A");
        MiHilo h2 = new MiHilo("Hilo-B");
        MiHilo h3 = new MiHilo("Hilo-C");

        h1.start();
        h2.start();
        h3.start();

        Grafico();
        GraficosConHilos();
    }
    
    public static void Grafico() {
        int[] arreglo = {90, 15, 42, 7, 63, 28, 55, 3, 77, 36};

        GraficaBarras gb = new GraficaBarras("Bubble Sort Visual", arreglo);
        gb.setSize(900, 500);
        gb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gb.setVisible(true);

        // Iniciar el hilo: 200ms entre cada paso
        BubbleSort hilo = new BubbleSort(arreglo, gb, 200);
        hilo.start();
    }
    
    public static void GraficosConHilos() {
        // Cada arreglo tiene sus propios datos
        int[][] arreglos = {
            {90, 15, 42, 7,  63, 28, 55,  3, 77, 36},
            {50, 80, 10, 95, 30, 70, 20, 60, 40, 85},
            {5,  99, 44, 22, 67, 11, 88, 33, 76, 55}
        };

        String[] titulos = {
            "Ventana 1 - Arreglo A",
            "Ventana 2 - Arreglo B",
            "Ventana 3 - Arreglo C"
        };

        int[] velocidades = {300, 150, 500}; // cada una a diferente velocidad

        for (int i = 0; i < arreglos.length; i++) {
            GraficaBarras gb = new GraficaBarras(titulos[i], arreglos[i]);
            gb.setSize(900, 500);
            gb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Posicionar ventanas en cascada para que no se encimen
            gb.setLocation(50 + i * 80, 50 + i * 80);
            gb.setVisible(true);

            // Hilo independiente para cada ventana
            BubbleSort hilo = new BubbleSort(arreglos[i], gb, velocidades[i]);
            hilo.start();
        }
    }
}
