
/**
 * This program has the user enter 3 values out of p, v, n, and t. It uses the ideal gas law to calculate the missing
 * value.
 * 
 * @author Nick Keirstead
 * @version 2/2/15
 */
import java.util.Scanner;
import java.math.BigDecimal;

public class IdealGas
{
   void init () {
       Scanner input = new Scanner (System.in);
       System.out.println("This program uses ideal gas law to find missing quantity.");
       System.out.println("Use atm for p, L for v, moles for n, K for t.");
       System.out.println();
       int findThis = findWhat(input);
       int numDecs = truncateHow(input);
       double[] vals = getVals(input,findThis);
       BigDecimal solution = calc(vals,findThis,numDecs);
       System.out.println(solution);
   }
   int findWhat (Scanner s) {
       System.out.println("Which value would you like to find? (p,v,n,t):  ");
       String mystery = s.nextLine();
       int mystVal = targetVar(mystery);
       return mystVal;
   }
   int truncateHow (Scanner s) {
       System.out.println("How many decimal places would you like the answer to?");
       int decs = s.nextInt();
       return decs;
   }
   double[] getVals (Scanner s, int mystVal) {
       String[] questions = new String[4];
       questions[0] = "Enter p val:  ";
       questions[1] = "Enter v val:  ";
       questions[2] = "Enter n val:  ";
       questions[3] = "Enter t val:  ";
       
       double[] answers = new double[4];
       for (int i = 0; i < questions.length; i++) {//fill answers array
           if (i == mystVal) {
               answers[i] = 1; //throw away val
           }
           else {
               System.out.println(questions[i]);
               answers[i] = s.nextDouble();
           }
       }
       return answers;
   }
   int targetVar (String myst) {
       int rval = 0;
       switch (myst) {
           case "p":
               rval = 0;
               break;
           case "v":
               rval = 1;
               break;
           case "n":
               rval = 2;
               break;
           case "t":
               rval = 3;
       }
       return rval;
   }
   BigDecimal calc (double[] vals, int mystVal, int truncVal) {
       double r = 0.082;
       double solution = 0.0;
       switch (mystVal) {
           case 0:
               solution = vals[2] * r * vals[3] / vals[1]; //p = nrt/v
               break;
           case 1:
               solution = vals[2] * r * vals[3] / vals[0]; //v = nrt/p
               break;
           case 2:
               solution = (vals[0] * vals[1]) / (r * vals[3]); //n = (pv)/(rt)
               break;
           case 3:
               solution = (vals[0] * vals[1]) / (r * vals[2]); //t = (pv)/(nr)
       }
       BigDecimal rval = truncateDecimal(solution,truncVal);
       return rval;
   }
   private static BigDecimal truncateDecimal(final double x, final int numberofDecimals) {
       return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_DOWN);
   }
}
