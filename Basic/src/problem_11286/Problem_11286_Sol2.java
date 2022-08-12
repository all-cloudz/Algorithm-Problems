package problem_11286;

import java.io.*;
import java.util.*;

public class Problem_11286_Sol2 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		PriorityQueue<Integer> absHeap = new PriorityQueue<>((a, b) -> {
			int compare = Math.abs(a) - Math.abs(b);
			
			if (compare == 0) {
				return a - b;
			}
			
			return compare;
		});
		
		final int N = Integer.parseInt(input.readLine());
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(input.readLine());
			
			if (cur != 0) {
				absHeap.offer(cur);
				continue;
			}
			
			if (absHeap.isEmpty()) {
				answer.append(0).append('\n');
				continue;
			}
			
			answer.append(absHeap.poll()).append('\n');
		}
		
		System.out.println(answer);
	}
}
