import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to calculate square root with Newton's method.
 *
 * @author Edison
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of makes epsilon
     *            accessible to square root method.
     * @return estimate of square root
     */

    private static double sqrt(double x) {
        double value;
        final double epsilon = 0.0001; /* relative error 0.01 */
        double r = x; /*
                       * double r = x because x is the formal parameter and r is
                       * the guess
                       */
        if (x == 0.0) {
            value = 0; /*
                        * if x is equal to 0, the value would be 0, else
                        * continue while loop
                        */
        } else {

            while (Math.abs((r * r) - x) / x > epsilon * epsilon) {
                r = (r + x / r) / 2;
            }
            /*
             * Math.abs takes the returns absolute number absolute value of
             * (r^2-x)/2 > e^2 because it checks how closer is to x by r^2 - x.
             * epsilon^2 to check relative error if it is greater than 0.0001
             *
             *
             * r = (r+x/r)/2 to get a better estimation of square root because r
             * is the what the estimate is, x is what it should be. so (r+x/r)
             * gives a better estimate for r, for example 25+26/25 = 26.04, x is
             * 26 so r gets closer to x
             */

            value = r;
        }
        return value;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        String continues = "y";

        out.println("Would you wish to calculate a square root?");
        continues = in.nextLine();
        while ("y".equals(continues)) { /* if input is y, continue while loop */
            out.println("Enter a value to calculate square root of: ");
            double x = in.nextDouble();
            out.println("The square root is: " + sqrt(x));
            out.println("Would you like to calculate another square root?");
            continues = in.nextLine();
            /* loop repeats until y is not inputed */
        }

        in.close();
        out.close();
    }

}
