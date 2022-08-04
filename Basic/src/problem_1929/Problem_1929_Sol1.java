package problem_1929;

import java.io.*;
import java.util.*;

public class Problem_1929_Sol1 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		final int M = Integer.parseInt(tokenizer.nextToken());
		final int N = Integer.parseInt(tokenizer.nextToken());
		
		int[] nums = new int[N + 1];
		for (int i = M; i <= N; i++) {
			nums[i] = i;
		}
		
		nums[1] = 0;
		for (int i = 2; i <= Math.sqrt(N); i++) {
			for (int j = i * i; j <= N; j += i) {
				nums[j] = 0;
			}
		}
		
		
		for (int i = M; i <= N; i++) {
			if (nums[i] != 0) {
				answer.append(nums[i]).append('\n');
			}
		}
		
		if (answer.length() == 0) {
			System.out.println(0);
			return;
		}
		
		System.out.println(answer);
	}
}
