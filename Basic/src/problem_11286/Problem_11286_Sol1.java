package problem_11286;

import java.io.*;
import java.util.*;

public class Problem_11286_Sol1 {
	private static class Pair implements Comparable<Pair> {
		private int abs;
		private int value;
		
		public Pair(int abs, int value) {
			this.abs = abs;
			this.value = value;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (this.abs == o.abs) {
				return this.value - o.value;
			}
			
			return this.abs - o.abs;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		PriorityQueue<Pair> absHeap = new PriorityQueue<>();
		
		final int N = Integer.parseInt(input.readLine());
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(input.readLine());
			
			if (cur != 0) {
				absHeap.offer(new Pair(Math.abs(cur), cur));
				continue;
			}
			
			if (absHeap.isEmpty()) {
				answer.append(0).append('\n');
				continue;
			}
			
			answer.append(absHeap.poll().value).append('\n');
		}
		
		System.out.println(answer);
	}
}
