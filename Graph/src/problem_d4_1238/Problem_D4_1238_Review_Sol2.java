package problem_d4_1238;

import java.io.*;
import java.util.*;

public class Problem_D4_1238_Review_Sol2 {
	private static class Node {
		private int to;
		private Node next;
		
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	private static Node[] adjLists;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			adjLists = new Node[101];
			
			StringTokenizer st = new StringTokenizer(input.readLine());
			st.nextToken();
			final int departure = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(input.readLine());
			while (st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjLists[from] = new Node(to, adjLists[from]);
			}
			
			int lastPerson = callHotLine(departure);
			answer.append(String.format("#%d %d%n", tc, lastPerson));
		}
		
		System.out.println(answer);
	}
	
	private static int callHotLine(int departure) {
		Queue<Integer> persons = new ArrayDeque<>();
		boolean[] discovered = new boolean[101];
		
		persons.offer(departure);
		discovered[departure] = true;
		
		int max = 0;
		while (!persons.isEmpty()) {
			max = 0;
			
			for (int levelSize = persons.size(), i = 0; i < levelSize; i++) {
				int cur = persons.poll();
				max = Math.max(max, cur);
				
				Node next = adjLists[cur];
				while (next != null) {
					if (!discovered[next.to]) {
						persons.offer(next.to);
						discovered[next.to] = true;
					}
					
					next = next.next;
				}
			}
		}
		
		return max;
	}
}
