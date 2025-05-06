import java.util.ArrayList;
public class StatsLibrary {

    //Calculates the mean of a given set
    public double mean(int[] data) {
        int total = 0;
        for(int i : data) { //runs through the given set
            total += i; //adds to the total to be divided.
        }
        return (double) total / data.length; //returns the mean (average).
    }

    //Calculates the median
    public String median(int[] data) {
        int mid = data.length / 2;
        if((data.length % 2) == 1) {
            return data[mid] + "";
        } else {
            return data[mid - 1] + " " + data[mid];
        }
    }

    //Calculates the mode
    public int mode(int[] data) {
        int max = 0;
        int modeVal = 0;
        for(int i = 0; i < data.length; i++) {
            int count = 0;
            for(int j = 0; j < data.length; j++) {
                if(data[j] == data[i]) {
                    count++;
                }
                if(count > max) {
                    max = count;
                    modeVal = data[i];
                }
            }
        }
        if(max == 1) {
            return 0;
        } else {
            return modeVal;
        }
    }

    //Calculates the standard deviation.
    public double standardDev(int[] data) {
        double sum = 0;
        double stDev = 0;
        for(int i = 0; i < data.length; i++) {
            sum += Math.pow(Math.abs((data[i] - mean(data))), 2);
        }
        stDev = sum / data.length;

        return Math.sqrt(stDev);
    }

    //Calculates factorial of a number
    public int factorial(int x) {
        int result = 1;
        for(int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    //Performs permutation of the given numbers.
    public double permutation(int a, int b) {
        return ((double) factorial(a) / factorial(a - b));
    }

    //Performs combination of the given numbers.
    public double combination(int a, int b) {
        return ((double) factorial(a) / (factorial(a - b) * factorial(b)));
    }

    //Performs binomial distribution for the given probabilities and failure/success rate
    public double binomialDistribution(int n, int x, double p, double q) {
        return combination(n, x) * Math.pow(p, x) * Math.pow(q, (n - x));
    }

    //Performs the expected and variance for the given probabilities in the Binomial Distribution.
    public void mvBinomial(int n, int x, double p, double q) {
        double mean = n * p;
        double variance = n * p * q;
        System.out.println("Expected and Variance of Binomial: \nExpected: " + mean + " Variance: " + variance);
    }

    //Performs geometric distribution for the given probabilities and failure/success rate
    public double geometricDistribution(int n, double p, double q) {
        return  Math.pow(q, n -1) * p;
    }

    //Performs the expected and variance for the given probabilities in the Geometric Distribution.
    public void mvGeometric(double p, double q) {
        double mean = 1/p;
        double variance = q/(Math.pow(p, 2));
        System.out.println("Expected and Variance of Geometric: \nExpected: " + mean + " Variance: " + variance);
    }

    //Performs Negative Binomial Distribution for the given probabilities and failure/success rate
    public double negativeBinomialDistribution(int y, int r, double p, double q) {
        return combination((y - 1), (r-1)) * Math.pow(p, r) * Math.pow(q, (y-r));
    }

    //Performs the expected and variance for the given probabilities in the Negative Binomial Distribution.
    public void mvNegativeBinom(int r, double p, double q) {
        double mean = r/p;
        double variance = (r * q)/Math.pow(p, 2);
        System.out.println("Expected and Variance of Negative Binomial: \nExpected: " + mean + " Variance: " + variance);
    }

    //Performs Hypergeometric Distribution for the given probabilities and failure/success rate
    public double hypergeometricDistribution(int N, int n, int r, int y) {
        return ((combination(r, y) * combination((N - r), (n - y)))/combination(N, n));
    }

    //Performs the expected and variance for the given probabilities in the Hypergeometric Distribution.
    public void mvHypergeometric(int N, int n, int r) {
        double mean = (double) (n * r) /N;
        double variance = ((double) r / N) * ((double) (N - r) / N) * ((double) (N - n) / (N - 1));
        System.out.println("Expected and Variance of Hypergeometric: \nExpected: " + mean + " Variance: " + variance);
    }

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


    //Performs set union
    public void union(ArrayList<Integer> a, ArrayList<Integer> b) {
       ArrayList<Integer> union = new ArrayList<>();
        for (int i : a) {
            union.add(i);
        }
        for (int i : b) {
            if(!union.contains(i)) {
                union.add(i);
            }
        }
        System.out.println("Union of two given sets: " + union);
    }

    //Performs set intersection
    public void intersection(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> intersection = new ArrayList<>();
        for(int i : a) {
            if(b.contains(i)) {
                intersection.add(i);
            }
        }
        System.out.println("Intersection of two given sets: " + intersection);
    }

    //Performs compliment of given set
    public void compliment(ArrayList<Integer> uni, ArrayList<Integer> a) {
        ArrayList<Integer> compliment = new ArrayList<>();

        for(int i : uni) {
            if(!a.contains(i)) {
                compliment.add(i);
            }
        }
        System.out.println("Compliment of the given set: " + compliment);
    }

    //Checks if the probabilities are independent of each other.
    public boolean independenceCheck(double a, double b, double ab) {
        return Math.abs(ab - (a * b)) < 0.0000001;
    }

    //Tests the methods.
    public void formulaTest() {
        int[] list = {1,2,3,4,5};
        ArrayList<Integer> uni = new ArrayList<>();
        for(int i = 1; i < 11; i++) {
            uni.add(i);
        }

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(4);

        ArrayList<Integer> b = new ArrayList<>();
        b.add(4);
        b.add(5);
        b.add(6);
        b.add(2);

        System.out.println("\n" + uni); //New line created to avoid confusion when running, as printed next to startup dialogue.
        System.out.println("The mean of the given list is: " + mean(list));
        System.out.println("The median of the given list is: " + median(list));
        System.out.println("The mode of the given list is: " + mode(list));
        System.out.println("The standard deviation of the given list is: " + standardDev(list));
        System.out.println("A Choose B: " + combination(5, 5));
        System.out.println("Binomial Distribution for given scenario: " + binomialDistribution(3, 2, (double) 1/6,(double) 5/6));
        mvBinomial(3, 2, (double) 1/6, (double) 5/6);
        System.out.println("Geometric distribution for given scenario: " + geometricDistribution(5, (double) 1/6, (double) 5/6));
        mvGeometric((double) 1/6, (double) 5/6);
        System.out.println("Poisson distribution for given scenario: " + poissonDist(2.5, 2));
        mvPoisson(2.5);
        chebyshevs(5, 2, 2);
        uniformDist(1, 19, 7, 15);
        mvUniform(1, 19);

        //Separating Set Functions from the mathematical functions.
        System.out.println("\nSets used in set operations: \n" + a + "\n" + b);
        union(a, b);
        intersection(a, b);
        compliment(uni, a);
        System.out.println("Are the events independent? " + independenceCheck(.5, .5, .25));
    }






}





















