//incomplete — doesn't yet support numbers between 0 and 1

import java.util.Scanner;
public class Untitled {
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Number To Convert To Sci. Notation: ");
		long n = s.nextLong();
		System.out.print("How Much Decimal Precision? ");
		int p = s.nextInt();
		System.out.println(toSciNotation(n,p));
	}
	public static String toSciNotation (long n, int precision) {
		//precision is the number of numbers after the decimal
		String num = cutLeadingZeroes(Long.toString(n));
		String front = num.substring(0,1);
		if (precision > 0) front += ".";
		for (int i = 1; i < precision + 1; i++) {
			if (i < num.length()) {
				//if can still get a digit from num to use after decimal
				front += num.substring(i,i+1);
			} else {
				front += "0";
			}
		}
		String exp = " x 10^" + (num.length() - 1);
		return front + exp;
	}
	public static String cutLeadingZeroes (String num) {
		if (num.equals("")) return "";
		if (num.charAt(0) != '0') return num;
		return cutLeadingZeroes(num.substring(1));
	}
}