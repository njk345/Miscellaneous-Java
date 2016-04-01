import java.util.*;
public class Permutations {
	public static <T> ArrayList<ArrayList<T>> perms(ArrayList<T> list) {
		if (list.size() == 0) {
			ArrayList<ArrayList<T>> result = new ArrayList<ArrayList<T>>();
			result.add(new ArrayList<T>());
			return result;
		}

		ArrayList<ArrayList<T>> returnMe = new ArrayList<ArrayList<T>>();

		T firstElement = list.remove(0);

		ArrayList<ArrayList<T>> recursiveReturn = perms(list);
		for (ArrayList<T> li : recursiveReturn) {
			for (int index = 0; index <= li.size(); index++) {
				ArrayList<T> temp = new ArrayList<T>(li);
			    temp.add(index, firstElement);
			    returnMe.add(temp);
			}
		}
		return returnMe;
	}
	
	public static void main (String[] args) {
		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		//test.add("4");
		//test.add("5");
		System.out.println(test.toString());
		System.out.println();
		System.out.println(perms(test).toString());
	}
}