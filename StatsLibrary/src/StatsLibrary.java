import java.util.ArrayList;
public class StatsLibrary {

    //Performs Poisson Distribution for the given values of lambda and y.
    public double poissonDist(double lambda, int y) {
        return (Math.pow(lambda, y) / factorial(y)) * Math.pow(Math.E, -lambda);
    }

    //Performs the expected and variance value calculation for Poisson, which is literally just lambda.
    public void mvPoisson(double lambda) {
        System.out.println("Expected and variance of Poisson: \nExpected: " + lambda + " Variance: " + lambda); //This is redundant lol.
    }

    //Performs the Tchebysheff's inequality function given the mean, standard deviation, and k.
    public void chebyshevs(double mean, double stDev, double k) {
        double lowBound = mean - k * stDev;
        double highBound = mean + k * stDev;
        double inequal = 1 - 1 / Math.pow(k, 2);

        System.out.println("At least " + inequal*100 + "% of values fall within [" + lowBound + "," + highBound + "]");
    }

    //Performs a Uniform Distribution calculation for given values a(theta1), b(theta2), c(low bound of integral), and d(high bound of integral).
    public double uniformDist(double a, double b, double c, double d) {
        return (d-c)/(b-a);
    }

    //Performs the expected and variance calculations for the Uniform Distribution.
    public void mvUniform(double a, double b) {
        double mean = (a + b) / 2;
        double variance = Math.pow((b - a), 2) / 12;
        System.out.println("Expected and variance of Uniform Distribution: \nExpected: " + mean + " Variance: " + variance);
    }

    //Tests the methods.
    public void formulaTest() {
        System.out.println("Poisson distribution for given scenario: " + poissonDist(2.5, 2));
        mvPoisson(2.5);
        chebyshevs(5, 2, 2);
        uniformDist(1, 19, 7, 15);
        mvUniform(1, 19);
    }
}





















