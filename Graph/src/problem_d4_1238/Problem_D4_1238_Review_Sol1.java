package problem_d4_1238;

import java.io.*;
import java.util.*;

public class Problem_D4_1238_Review_Sol1 {
	private static boolean[][] adjMatrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			adjMatrix = new boolean[101][101];
			
			StringTokenizer st = new StringTokenizer(input.readLine());
			st.nextToken();
			final int departure = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(input.readLine());
			while (st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjMatrix[from][to] = true;
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
				
				for (int next = 0; next < 101; next++) {
					if (!adjMatrix[cur][next]) {
						continue;
					}
					
					if (!discovered[next]) {
						persons.add(next);
						discovered[next] = true;
					}
				}
			}
		}
		
		return max;
	}
}
