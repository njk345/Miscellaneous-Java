//very specific purpose of figuring out what my first trimester chem grade will be based on my grades on the last four assignments.

import java.lang.*;

class chemGrade {
	public static void main (String[] grades) {
		double firstLab = Double.parseDouble(grades[0]);
		double secondLab = Double.parseDouble(grades[1]);
		double lastTest = Double.parseDouble(grades[2]);
		double lastHw = Double.parseDouble(grades[3]);
		
		double overallGrade = calcAvg(firstLab, secondLab, lastTest, lastHw);
		System.out.println(overallGrade * 100);
		
	}
	public static double calcAvg (double lab1, double lab2, double test, double hw) {
		double testAvg = (0.982142 + 0.945454 + test)/3;
		System.out.println(testAvg);
		double labAvg = (0.95 + 4.0 + lab1 + lab2)/7;
		double quizAvg = 1.0;
		double hwAvg = (0.923076 + 2.0 + hw)/4;
		
		return testAvg * 0.5 + labAvg * .25 + quizAvg * 0.1 + hwAvg * 0.15; //weighted overall grade
	}
}