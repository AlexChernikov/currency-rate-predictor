package ru.digitalleague.predictor.servicies.formatters;

import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.interfaces.Formatter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
public class JFreeFormatterService implements Formatter {

    private final int width = 1920;
    private final int height = 1080;

    @Override
    public PredictionResult buildPredication(Map<Currency, List<BigDecimal>> currencyAndCounts, LocalDateTime localDateTime) {
        log.info("build predication started");
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (Currency currency : currencyAndCounts.keySet()) {
            XYSeries xySeries = new XYSeries(currency.toString());

            int x = 0;
            for (BigDecimal count : currencyAndCounts.get(currency)) {
                x++;
                xySeries.add(x, count);
            }

            dataset.addSeries(xySeries);
        }

        JFreeChart jFreeChart = ChartFactory.createXYLineChart("Курс валют", "Число", "Курс", dataset);

        XYPlot plot = jFreeChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesPaint(3, Color.CYAN);
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));
        renderer.setSeriesPaint(4, Color.MAGENTA);
        renderer.setSeriesStroke(4, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        jFreeChart.getLegend().setFrame(BlockBorder.NONE);

        BufferedImage bufferedImage = jFreeChart.createBufferedImage(width, height);

        log.info("build predication finished");
        return new PredictionResult("Граф предсказания", bufferedImage);
    }
}
