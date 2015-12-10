import java.util.Scanner;
class Untitled {
	public static void main(String[] args) {
		while (true) {
			Scanner s = new Scanner(System.in);
			System.out.println("Enter s1: ");
			String s1 = s.nextLine();
			System.out.println("Enter s2: ");
			String s2 = s.nextLine();
			int res = compareWith(s1,s2);
			if (res == -2) System.out.println("Invalid Input");
			else {
				String op = res == -1? "S1 comes first" : res == 1? "S2 comes first" : "S1 = S2";
				System.out.println(op);
			}
		}
	}
	public static int compareWith(String a, String b) {
		if (a.equals(b)) return 0;
		if (a.equals("") && !b.equals("")) return 1;
		if (!a.equals("") && b.equals("")) return -1;
		if (!Character.isLetter(a.charAt(0)) || !Character.isLetter(b.charAt(0)))
			return -2;
		if (a.charAt(0) > b.charAt(0)) return -1;
		if (a.charAt(0) < b.charAt(0)) return 1;
		return compareWith(a.substring(1), b.substring(1));
	}
}