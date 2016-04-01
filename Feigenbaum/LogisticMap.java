import java.awt.RenderingHints;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class LogisticMap extends ApplicationFrame {

    /** A constant for the number of items in the sample dataset. */
    private static final int COUNT = 2000 * 1024;

    /** The data. */
    private float[][] data = new float[2][COUNT];

    /**
     * Creates a new fast scatter plot demo.
     *
     * @param title  the frame title.
     */
    public LogisticMap(final String title) {
        super(title);
        populateData();
        final NumberAxis domainAxis = new NumberAxis("X");
        domainAxis.setAutoRangeIncludesZero(false);
        final NumberAxis rangeAxis = new NumberAxis("Y");
        rangeAxis.setAutoRangeIncludesZero(false);
        final FastScatterPlot plot = new FastScatterPlot(this.data, domainAxis, rangeAxis);
        final JFreeChart chart = new JFreeChart("Fast Scatter Plot", plot);
        //        chart.setLegend(null);

        // force aliasing of the rendered content..
        chart.getRenderingHints().put
        (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final ChartPanel panel = new ChartPanel(chart, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        //      panel.setHorizontalZoom(true);
        //    panel.setVerticalZoom(true);
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(20);
        panel.setMaximumDrawWidth(2000);

        setContentPane(panel);

    }
    /**
     * Populates the data array with random values.
     */
    private void populateData() {
        for (int i = 0; i < 2000; i++) {
            float r = i * (float)0.001;
            float x = (float)0.12345;
            for (int j = 0; j < 1000; j++) {
                x = 1 - r * x * x;
            }
            for (int j = 0; j < 1024; j++) {
                x = 1 - r * x * x;
                data[0][128 * i + j] = r;
                data[1][128 * i + j] = x;
            }
        }
    }
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {
        final LogisticMap graph = new LogisticMap("Logistic Map");
        graph.pack();
        RefineryUtilities.centerFrameOnScreen(graph);
        graph.setVisible(true);
    }
}
