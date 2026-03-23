package Graficas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficaBarras extends JFrame {

    private DefaultCategoryDataset dataset;
    private BarRenderer renderer;
    private int indicadorI = -1; // barra que se está comparando
    private int indicadorJ = -1; // barra comparada

    public GraficaBarras(String titulo, int[] arreglo) {
        super(titulo);

        dataset = crearDataSet(arreglo);

        JFreeChart chart = ChartFactory.createBarChart(
            "Bubble Sort en tiempo real",
            "Índice",
            "Valor",
            dataset,
            PlotOrientation.VERTICAL,
            false, true, false
        );

        personalizarGrafico(chart);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(900, 500));
        setContentPane(panel);
        this.setLocationRelativeTo(null);
    }

    public DefaultCategoryDataset crearDataSet(int[] arreglo) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (int i = 0; i < arreglo.length; i++) {
            // 
            ds.addValue(arreglo[i], "Valor", "Pos " + i);
        }
        return ds;
    }

    // Actualiza una posición específica del dataset
    public void actualizarPosicion(int index, int valor, int i, int j) {
        dataset.setValue(valor, "Valor", "Pos " + index);
        this.indicadorI = i;
        this.indicadorJ = j;
        pintarBarras();
    }

    // Colorea las barras: rojo = comparando, verde = resto
    private void pintarBarras() {
        int count = dataset.getColumnCount();
        for (int k = 0; k < count; k++) {
            if (k == indicadorI || k == indicadorJ) {
                renderer.setSeriesPaint(0, Color.RED); // no aplica por índice de columna
            }
        }
        // Usar ItemPainter por columna
        renderer.setSeriesItemLabelGenerator(0, null);
    }

    private void personalizarGrafico(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();

        renderer = new BarRenderer() {
            @Override
            public java.awt.Paint getItemPaint(int row, int column) {
                if (column == indicadorI || column == indicadorJ) {
                    return Color.RED;       // barras siendo comparadas
                }
                return new Color(70, 130, 180); // azul normal
            }
        };

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 13));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 13));
    }
    
    public void marcarOrdenado() {
    indicadorI = -1;
    indicadorJ = -1;
    // Forzar repintado con color verde
    renderer = new BarRenderer() {
        @Override
        public java.awt.Paint getItemPaint(int row, int column) {
            return new Color(34, 180, 100); // verde = ordenado
        }
    };
    ((CategoryPlot) ((ChartPanel) getContentPane()).getChart().getPlot())
        .setRenderer(renderer);
}
}