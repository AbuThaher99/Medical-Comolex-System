package org.example.ProjectTraninng.Core.Components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.block.BlockBorder;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class ChartGenerator {

    public void generateChart(Map<String, Double> totalAmountByMonth, String filePath) throws IOException {
        CategoryDataset dataset = createDataset(totalAmountByMonth);
        JFreeChart chart = createChart(dataset);

        File chartFile = new File(filePath);
        ChartUtilities.saveChartAsPNG(chartFile, chart, 800, 600);
    }

    private CategoryDataset createDataset(Map<String, Double> totalAmountByMonth) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : totalAmountByMonth.entrySet()) {
            dataset.addValue(entry.getValue(), "Total Amount", entry.getKey());
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Salary Payments",
                "Month",
                "Total Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        TextTitle title = new TextTitle("Monthly Salary Payments", new Font("Serif", Font.BOLD, 24));
        chart.setTitle(title);

        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        plot.setDomainAxis(new CategoryAxis("Month"));
        plot.setRangeAxis(new NumberAxis("Total Amount"));

        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 10));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 14));

        CustomBarRenderer renderer = new CustomBarRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelFont(new Font("SansSerif", Font.BOLD, 12));
        renderer.setDrawBarOutline(false);

        plot.setRenderer(renderer);
        chart.setBorderVisible(false);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }

    class CustomBarRenderer extends BarRenderer {
        private final Color[] colors = {
                Color.decode("#FF0000"), // Jan
                Color.decode("#FFFF00"), // Feb
                Color.decode("#00FF00"), // Mar
                Color.decode("#FFC0CB"), // Apr
                Color.decode("#0000FF"), // May
                Color.decode("#A52A2A"), // Jun
                Color.decode("#C7FF33"), // Jul
                Color.decode("#CA33FF"), // Aug
                Color.decode("#FF336B"), // Sep
                Color.decode("#33FFE3"), // Oct
                Color.decode("#E0FF33"), // Nov
                Color.decode("#C433FF")  // Dec
        };

        @Override
        public Paint getItemPaint(int row, int column) {
            return colors[column % colors.length];
        }
    }
}
