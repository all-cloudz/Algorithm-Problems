package problem_4195;

import java.io.*;
import java.util.*;

// 그래프를 만들어서 접근하면 사람을 추가할 때마다 순회를 해야하므로 쿼리가 100_000개 일때는 시간 초과가 발생한다.
public class Problem_4195_Fail {
	private static class Graph {
		private HashMap<String, HashSet<String>> adjList = new HashMap<>();
		
		public void addCompleteEdge(String vertex1, String vertex2) {
			if (adjList.getOrDefault(vertex1, null) == null) {
				adjList.put(vertex1, new HashSet<>());
			}
			
			if (adjList.getOrDefault(vertex2, null) == null) {
				adjList.put(vertex2, new HashSet<>());
			}
			
			adjList.get(vertex1).add(vertex2);
			adjList.get(vertex2).add(vertex1);
		}
		
		public int countFriends(String f1, String f2) {
			Queue<String> friends = new LinkedList<>();
			HashSet<String> discovered = new HashSet<>();
			
			friends.add(f1);
			discovered.add(f1);
			int cnt = 1;
			
			while (!friends.isEmpty()) {
				String cur = friends.poll();
				
				for (String next : adjList.get(cur)) {
					if (discovered.contains(next)) {
						continue;
					}
					
					friends.add(next);
					discovered.add(next);
					cnt++;
				}
			}
			
			return cnt;
		}
	}

	private static StringBuilder answer = new StringBuilder();
	private static Graph network;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(input.readLine());
		
		while (T-- > 0) {
			network = new Graph();
			int F = Integer.parseInt(input.readLine());
			
			while (F-- > 0) {
				String[] friends = input.readLine().split(" ");
				network.addCompleteEdge(friends[0], friends[1]);
				answer.append(network.countFriends(friends[0], friends[1])).append('\n');
			}
		}
		
		System.out.println(answer);
	}
}
