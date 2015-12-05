class RecursiveFizzBuzz {
	public static void main(String[] args) {
		System.out.println(fizzBuzz(1));
	}
	public static String fizzBuzz(int n) {
		String rv = "";
		if (n == 101) return rv;
		if (n % 3 == 0 && n % 5 == 0) rv = "FizzBuzz\n";
		else if (n % 3 == 0) rv = "Fizz\n";
		else if (n % 5 == 0) rv = "Buzz\n";
		else rv = n + "\n";
		return rv + fizzBuzz(n+1);
	}
}