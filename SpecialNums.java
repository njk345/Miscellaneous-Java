import java.util.ArrayList;

class SpecialNums {
	public static void main(String[] args) {
		System.out.println(nextSpecialNum(1));
		System.out.println(nextSpecialNum(6));
		System.out.println(nextSpecialNum(28));
		System.out.println(nextSpecialNum(496));
		System.out.println(nextSpecialNum(8128));
	}
	public static int nextSpecialNum(int n) {
		int i = n+1;
		while (!isSpecial(i)) {
			i++;
		}
		return i;
	}
	
	public static boolean isSpecial(int n) {
		ArrayList<Integer> facts = getFacts(n);
		int sum = 0;
		for (int i = 0; i < facts.size(); i++) {
			sum += facts.get(i);
		}
		return (sum == n);
	}
	
	public static ArrayList<Integer> getFacts(int n) {
		ArrayList<Integer> factors = new ArrayList<Integer>();
		int i = 1;
		while (i < n) {
			if (n % i == 0) {
				factors.add(i);
			}
			i++;
		}
		return factors;
	}
}