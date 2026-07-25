package Algoritmos;

import Graficas.GraficaBarras;
import javax.swing.SwingUtilities;

public class BubbleSort extends Thread {

    private int[] arreglo;
    private GraficaBarras grafica;
    private int velocidad; // milisegundos entre cada paso

    public BubbleSort(int[] arreglo, GraficaBarras grafica, int velocidadMs) {
        this.arreglo = arreglo.clone(); // copia para no modificar el original
        this.grafica = grafica;
        this.velocidad = velocidadMs;
    }

    @Override
    public void run() {
        int n = arreglo.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                // Marcar las dos barras que se están comparando
                final int fi = j, fj = j + 1;

                if (arreglo[j] > arreglo[j + 1]) {
                    // Intercambiar
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;

                    // Actualizar la gráfica en el hilo de Swing
                    final int val1 = arreglo[j];
                    final int val2 = arreglo[j + 1];

                    SwingUtilities.invokeLater(() -> {
                        grafica.actualizarPosicion(fi, val1, fi, fj);
                        grafica.actualizarPosicion(fj, val2, fi, fj);
                    });
                } else {
                    // Solo resaltar sin intercambiar
                    SwingUtilities.invokeLater(() ->
                        grafica.actualizarPosicion(fi, arreglo[fi], fi, fj)
                    );
                }

                // Pausa para que se vea el movimiento
                try {
                    Thread.sleep(velocidad); // 1000
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        // Al terminar: todo verde
        SwingUtilities.invokeLater(() -> grafica.marcarOrdenado());
    }
}