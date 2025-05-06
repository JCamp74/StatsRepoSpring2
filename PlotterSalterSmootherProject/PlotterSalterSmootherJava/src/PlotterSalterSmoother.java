import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * PlotterSalterSmoother class. Contains methods to plot,
 * salt, and smooth given data. Constructor sets the original function, and each
 * method runs based off of the constructor's configuration.
 * 
 * Credits to Jessica Smith with assistance in setting up the java.io reading and writing for files.
 * 
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class PlotterSalterSmoother {
    
    private double x;
    private double y;
    private ArrayList<Double> xArray = new ArrayList<>();
    private ArrayList<Double> yPlotArray = new ArrayList<>();
    private ArrayList<Double> ySaltArray = new ArrayList<>();
    private ArrayList<Double> ySmoothArray = new ArrayList<>();

    /**
     * PlotterSalterSmoother constructor. Sets the original function
     * and adds the data into xArray and yPlotArray as the original function data.
     * Created function is y = x^2 + 2.
     */
    public PlotterSalterSmoother() {
        x = -20;
        y = Math.pow(x, 2) + 2;

        for(double i = -20; i < 20.25; i += .25) {
            xArray.add(x);
            yPlotArray.add(y);
            x += .25;
            y = Math.pow(x, 2) + 2;
        }
    }

    /**
     * Plotter method. Takes the data from xArray and yPlotArray to create a CSV file
     * with plotted data. Plots the function into a table format.
     */
    public void plotter() {
       try {
            FileWriter newFile = new FileWriter("DataValuesPlotted.csv");
            PrintWriter output = new PrintWriter(newFile);
            for(int i = 0; i < xArray.size(); i++) {
                output.println(xArray.get(i) + "," + yPlotArray.get(i));
            }
        output.close();
        } catch (IOException e) {
            System.out.println("Womp womp");
        }
    }

    /**
     * Salter method. Takes the data from the DataValuesPlotted CSV, parsing past the first comma
     * to add data into ySaltArray. 
     * Then, takes the data and moves through each index, adding a random value from -100 to 100.
     * Creates a new CSV file with xArray and ySaltArray's data.
     */
    public void salter() {
        try {
            BufferedReader read = new BufferedReader(new FileReader("DataValuesPlotted.csv"));
            String line = read.readLine();

            while (line != null) {
                double y = Double.parseDouble(line.split(",")[1].trim());

                ySaltArray.add(y);

                line = read.readLine();
            }
            read.close();
        } catch (IOException e) {
            System.out.println("Womp womp 2");
        }
        
        Random rng = new Random();
        for(int i = 0; i < ySaltArray.size(); i++) {
            double temp = ySaltArray.get(i);
            temp = temp + rng.nextDouble(-100, 100);
            ySaltArray.set(i, temp);
        }

        try {
            FileWriter newFile = new FileWriter("DataValuesSalted.csv");
            PrintWriter output = new PrintWriter(newFile);
            for(int i = 0; i < xArray.size(); i++) {
                output.println(xArray.get(i) + "," + ySaltArray.get(i));
            }
        output.close();
        } catch (IOException e) {
            System.out.println("Womp womp 3");
        }
    }

    /**
     * Smoother method. Takes the data from the DataValuesSalted CSV, parsing past the first comma
     * to add data into ySmoothArray.
     * Sets a rolling bound of 2 (up/down) to take the rolling average. Once the data is added,
     * the rolling average is created and set for each index in ySmoothArray. 
     * Creates a new CSV file with xArray and ySmoothArray's data.
     */
    public void smoother() {
        int upDownWindow = 2;
        double rollAvg = 0;
        double numOfValues = 0;
        try {
            BufferedReader read = new BufferedReader(new FileReader("DataValuesSalted.csv"));
            String line = read.readLine();

            while (line != null) {
                double y = Double.parseDouble(line.split(",")[1].trim());

                ySaltArray.add(y);

                line = read.readLine();
            }
            read.close();
        } catch (IOException e) {
            System.out.println("Womp womp 2");
        }
        
        for(int i = 0; i < ySaltArray.size(); i++) {
            ySmoothArray.add(ySaltArray.get(i));
        }

        for(int i = 0; i < ySmoothArray.size(); i++) {
            int lowBound = i - upDownWindow;
            int highBound = i + upDownWindow;
            if(lowBound < 0) {
                lowBound = 0;
            }
            if(highBound > ySmoothArray.size()) {
                highBound = ySmoothArray.size();
            }
            for(int j = lowBound; j < highBound; j++) {
                rollAvg += ySmoothArray.get(j);
                numOfValues++;
            }
            rollAvg = rollAvg / numOfValues;
            ySmoothArray.set(i, rollAvg);
            numOfValues = 0;
            rollAvg = 0;
        }

        try {
            FileWriter newFile = new FileWriter("DataValuesSmoothed.csv");
            PrintWriter output = new PrintWriter(newFile);
            for(int i = 0; i < xArray.size(); i++) {
                output.println(xArray.get(i) + "," + ySmoothArray.get(i));
            }
        output.close();
        } catch (IOException e) {
            System.out.println("Womp womp 4");
        }


    }

    /**
     * Run method to run the plotter, salter and smoother methods to create each CSV file.
     */
    public void run() {
        plotter();
        salter();
        smoother();
    }
}