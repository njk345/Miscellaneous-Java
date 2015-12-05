public class MakeTags {
	public static void main (String[] args) {
		String out = makeTags("stuff", "hello");
		System.out.println(out);
	}
	public static String makeTags(String tag, String word) {
	   String tagFront = "<" + tag + ">";
	   String tagEnd = "</" + tag + ">";
	   String rv = tagFront + word + tagEnd;
	   return rv;
	}
}

