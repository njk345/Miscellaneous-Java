import java.time.*;
import java.time.temporal.*;

public class Clock {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		int hour = now.get(ChronoField.HOUR_OF_DAY);
		int minute = now.get(ChronoField.MINUTE_OF_HOUR);
		int month = now.get(ChronoField.MONTH_OF_YEAR);
		int day = now.get(ChronoField.DAY_OF_MONTH);
		int year = now.get(ChronoField.YEAR);
		
		if (hour < 12) {
			System.out.println("Good Morning.\n");
		}
		else if (hour < 17) {
			System.out.println("Good afternoon.\n");
		}
		else {
			System.out.println("Good evening.\n");
		}
		
		System.out.print("It's");
		if (minute != 0) {
			System.out.print(" " + minute + " ");
			System.out.print((minute != 1) ? "minutes" : "minute");
			System.out.print(" past ");
		}
		
		System.out.print((hour > 12) ? (hour - 12) : hour);
		System.out.print(" o'clock on the ");
		System.out.print(day);
		
		switch (month) {
			case 1: case 2: case 3: case 11: case 12:
				System.out.print(" of some cold-ass month, ");
				break;
			case 4: case 5: case 6:
				System.out.print(" of some warm month, ");
				break;
			case 7: case 8:
				System.out.print(" of some freakin' hot month, ");
				break;
			case 9: case 10:
				System.out.print(" of some autumn month, ");
			
			/*case 5:
				System.out.print("May");
				break;
			case 6:
				System.out.print("June");
				break;
			case 7:
				System.out.print("July");
				break;
			case 8:
				System.out.print("August");
				break;
			case 9:
				System.out.print("September");
				break;
			case 10:
				System.out.print("October");
				break;
			case 11:
				System.out.print("November");
				break;
			case 12:
				System.out.print("December");
			*/
		}
		
		System.out.println(year + ".");
	}
}