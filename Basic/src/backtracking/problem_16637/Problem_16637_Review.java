package backtracking.problem_16637;

import java.io.*;

public class Problem_16637_Review {
	private static int N;
	private static String formula;
	private static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(input.readLine());
		formula = input.readLine();
		max = Integer.MIN_VALUE;

		setMax(1, formula.charAt(0) - '0');
		System.out.println(max);
	}

	private static void setMax(int idx, int cal) {
		if (idx >= N) {
			max = Math.max(max, cal);
			return;
		}

		int nextCal = calculate(cal, formula.charAt(idx), formula.charAt(idx + 1) - '0');
		setMax(idx + 2, nextCal);
		
		if (idx < N - 3) {
			int bracketCal = calculate(formula.charAt(idx + 1) - '0', formula.charAt(idx + 2), formula.charAt(idx + 3) - '0');
			nextCal = calculate(cal, formula.charAt(idx), bracketCal);
			setMax(idx + 4, nextCal);
		}
	}

	private static int calculate(int pre, char operator, int post) {
		if (operator == '+') {
			return pre + post;
		}

		if (operator == '-') {
			return pre - post;
		}

		return pre * post;
	}
}
