/**
 * Created by pwd on 2017/1/31.
 */
public class TestPlanet {

    public static void main(String[] args) {

        Planet p1 = new Planet(1.00, 2.00, 4.00, 5.00, 3000, "Good Planet");
        Planet p2 = new Planet(2.00, 1.00, 3.00, 5.00, 4000, "Bad Planet");



    }


    /**
     * Checks whether or not two Doubles are equal and prints the result.
     *
     * @param expected Expected double
     * @param actual   Double received
     * @param label    Label for the 'test' case
     * @param eps      Tolerance for the double comparison.
     */
    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }


}
