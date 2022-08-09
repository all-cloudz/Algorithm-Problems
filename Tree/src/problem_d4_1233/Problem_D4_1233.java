package problem_d4_1233;

import java.io.*;
import java.util.*;

public class Problem_D4_1233 {
	private static final Set<String> OPERATORS;
	private static String[] completeBinaryTree;
	
	static {
		OPERATORS = new HashSet<>();
		OPERATORS.add("+");
		OPERATORS.add("-");
		OPERATORS.add("*");
		OPERATORS.add("/");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			final int N = Integer.parseInt(input.readLine());
			completeBinaryTree = new String[N + 1];
			
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(input.readLine());
				int index = Integer.parseInt(st.nextToken());
				String value = st.nextToken();
				completeBinaryTree[index] = value;
			}
			
			if (validate(N)) {
				answer.append((String.format("#%d 1%n", tc)));
				continue;
			}
			
			answer.append((String.format("#%d 0%n", tc)));
		}
		
		System.out.println(answer);
	}
	
	private static boolean validate(int N) {
		for (int i = 1; i <= (N >> 1); i++) {
			if (!OPERATORS.contains(completeBinaryTree[i])) {
				return false;
			}
		}
		
		for (int i = 1 + (N >> 1); i <= N; i++) {
			if (OPERATORS.contains(completeBinaryTree[i])) {
				return false;
			}
		}
		
		return true;
	}
}
