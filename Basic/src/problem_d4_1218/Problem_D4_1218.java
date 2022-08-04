package problem_d4_1218;

import java.io.*;
import java.util.*;

public class Problem_D4_1218 {
	private static final HashMap<Character, Character> match;
	private static StringBuilder answer;
	
	static {
		match = new HashMap<>();
		match.put(')', '(');
		match.put('}', '{');
		match.put(']', '[');
		match.put('>', '<');
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			input.readLine();
			char[] brackets = input.readLine().toCharArray();
			
			if (validate(brackets)) {
				answer.append(String.format("#%d 1%n", tc));
				continue;
			}
			
			answer.append(String.format("#%d 0%n", tc));
		}
		
		System.out.println(answer);
	}

	private static boolean validate(char[] brackets) {
		Stack<Character> checker = new Stack<>();

		for (char cur : brackets) {
			if (checker.isEmpty() || !match.containsKey(cur)) {
				checker.push(cur);
				continue;
			}

			if (!match.get(cur).equals(checker.peek())) {
				break;
			}

			checker.pop();
		}

		return checker.isEmpty();
	}
}
