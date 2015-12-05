class MultsOf13 {
	public static void main(String[] args) {
		int i = 13;
		int mults = 0;
		while (mults != 400) {
			if (i % 13 == 0) {
				mults++;
				System.out.println(i);
			}
			i++;
		}
	}
}