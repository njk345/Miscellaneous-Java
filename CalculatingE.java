import java.util.*;
public class CalculatingE {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Spend How Many Seconds Calculating e? ");
		int sec = s.nextInt();
		System.out.println(calcE(sec));
	}
	public static double calcE(int sec) {
		double val = 0;
		long t0 = System.currentTimeMillis();
		long t = System.currentTimeMillis();
		int n = 0;
		while ((t - t0) / 1000.0 < sec) {
			val = Math.pow(1 + ((double)1 / n), n);
			System.out.println(val);
			t = System.currentTimeMillis();
			n++;
		}
		System.out.println("Done After " + n + " Iterations.");
		return val;
	}
	public static double fact(int n) {
		if (n <= 2) return 0;
		return n * fact(n-1);
	}
}