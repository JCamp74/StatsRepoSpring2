package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * PSSMavenFreeChart class. Contains XYSeries functions to plot,
 * salt and smooth any given data set in the arrayLists.
 * All imports for JFreeChart are used properly and are listed at the top.
 * 
 * Credit to Jessica Smith for assistance in setting the XML file.
 * 
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class PSSMavenFreeChart {

    private ArrayList<Double> xArray = new ArrayList<>();
    private ArrayList<Double> yPlotted = new ArrayList<>();
    private ArrayList<Double> ySalted = new ArrayList<>();
    private ArrayList<Double> ySmoothed = new ArrayList<>();
    private double x;
    private double y;

    /**
     * PSSMavenFreeChart constructor. Sets the original x value ArrayList,
     * and creates the original function output in the yPlotted ArrayList.
     * Iterates in .25 increments from -20 to 20 to plot the function.
     */
    public PSSMavenFreeChart() {
        x = -20;
        y = Math.pow(x, 2) + 2;

        for (double i = -20; i < 20.25; i += .25) {
            xArray.add(x);
            yPlotted.add(y);
            x += .25;
            y = Math.pow(x, 2) + 2;
        }
    }

    /**
     * XYSeries Plotter. Returns a basic function in the given XYSeries.
     * 
     * @return the newly created XYSeries function.
     */
    public XYSeries plot() {
        XYSeries origin = new XYSeries("Original Plotted Data");
        for(int i = 0; i < xArray.size(); i++) {
            origin.add(xArray.get(i), yPlotted.get(i));
        }
        return origin;
    }

    /**
     * XYSeries Salter. Salts the data set and adds the new values to the "ySalted"
     * ArrayList. Adds a random value to each output, ranging from -100 to 100.
     * 
     * @return the created salted XYSeries.
     */
    public XYSeries salt() {
        XYSeries saltSeries = new XYSeries("Salted Data from Original Plot");
        Random rng = new Random();
        for(int i = 0; i < xArray.size(); i++) {
            ySalted.add(yPlotted.get(i) + rng.nextDouble() * 200 - 100);
        }
        for(int i = 0; i < xArray.size(); i++) {
            saltSeries.add(xArray.get(i), ySalted.get(i));
        }
        return saltSeries;
    }

    /**
     * XYSeries Smoother. Smooths the data created in the "salt" function.
     * Takes a rolling average with a range of 5(up/down) to smooth the data out.
     * 
     * @return the smoothed data XYSeries.
     */
    public XYSeries smooth() {
        DescriptiveStatistics ds = new DescriptiveStatistics();
        XYSeries smoothSeries = new XYSeries("Smoothed Data from Previous Salting");
        int window = 5;
        int count = 0;
        ds.setWindowSize(window);
        
        for(Double y : ySalted) {
            ds.addValue(y);
            if(count >= window) {
                ySmoothed.add(ds.getMean());
            }
            count++;
        }
        
        /* 
        for(int i = 0; i < ySalted.size(); i++) {
            double rollAvg = 0;
            double numOfValues = 0;
            int lowBound = i - window;
            int highBound = i + window;
            if(lowBound < 0) {
                lowBound = 0;
            }
            if(highBound > ySalted.size()) {
                highBound = ySalted.size();
            }
            for(int j = lowBound; j < highBound; j++) {
                rollAvg += ySalted.get(j);
                numOfValues++;
            }
            double temp = rollAvg / numOfValues;
            ySmoothed.add(temp);
        }
        */

        for(int i = 0; i < ySmoothed.size(); i++) {
            smoothSeries.add(xArray.get(i), ySmoothed.get(i));
        }
        return smoothSeries;
    }

    /**
     * Method to run all three XYSeries creators, and add them to the 
     * XYSeriesCollection. Once added to "dataSet", the method then uses JFreeChart
     * to create a title, lables for each axis, and plots the functions on a set axis.
     */
    public void allThree() {
        XYSeriesCollection dataSet = new XYSeriesCollection();
        dataSet.addSeries(plot());
        dataSet.addSeries(salt());
        dataSet.addSeries(smooth());

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Plotter, Salter, and Smoother",
                "X-Axis",
                "Y-Axis",
                dataSet
        );

        XYPlot plot = chart.getXYPlot();
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();

        xAxis.setRange(-20, 20);
        yAxis.setRange(-98, Math.pow(20, 2) + 2 + 100);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); // Set preferred size

        JFrame frame = new JFrame("Data Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Run Method. Runs the "allThree" method in the invokeLater area 
     * to update the GUI accordingly.
     */
    public void run() {
        SwingUtilities.invokeLater(() -> {
            allThree();
        });
    }

}
