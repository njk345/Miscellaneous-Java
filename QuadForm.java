import java.util.Scanner;

public class QuadForm {
    public static String findRoots(double a, double b, double c) {
        double discrim = b*b - 4*a*c;
        if (discrim < 0) {
            double realPart = -b / (2*a);
            double imagPart = Math.sqrt(-discrim) / (2*a);
            return "The roots are x = " + realPart + " +- " + imagPart + "i";
        } else {
            double x1 = (-b + Math.sqrt(discrim)) / (2*a);
            double x2 = (-b - Math.sqrt(discrim)) / (2*a);
            if (discrim == 0) {
                return "There is a double root at x = " + x1;
            } else {
                return "The roots are x = " + x1 + " and x = " + x2;
            }
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        String[] abcStrs = s.nextLine().split(",");
        double[] abc = new double[3];
        for (int i = 0; i < 3; i++) {
            abc[i] = Double.valueOf(abcStrs[i].trim());
        }
        System.out.println(findRoots(abc[0], abc[1], abc[2]));
    }
}