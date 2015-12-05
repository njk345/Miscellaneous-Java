import java.util.Scanner;
import java.util.ArrayList;

class MemoizedFib {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Integer> solutions = new ArrayList<Integer>();
		solutions.add(1);
		solutions.add(1);
		System.out.print("Enter Nth Factorial To Be Found: ");
		int n = s.nextInt();
		System.out.println(fib(solutions, n));
	}
	public static int fib (ArrayList<Integer> store, int n) {
		if (n < store.size()) {
			return store.get(n);
		}
		int newFib = fib(store, n-1) + fib(store, n-2);
		store.add(newFib);
		return newFib;
	}
}