package problem_17471;

import java.io.*;
import java.util.*;

public class Problem_17471_BFS {

	private static class Area {
		private int label;
		private int numOfPeople;
		private List<Area> adjList;

		public Area(int label, int numOfPeople) {
			this.label = label;
			this.numOfPeople = numOfPeople;
			adjList = new ArrayList<>();
		}
	}
	
	private static int N;
	private static Area[] city;
	private static int totalNumOfPeople;
	private static int minDiff;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(input.readLine());
		city = new Area[N];

		StringTokenizer st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			int numOfPeople = Integer.parseInt(st.nextToken());
			city[i] = new Area(i, numOfPeople);
			totalNumOfPeople += numOfPeople; 
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			int sizeOfAdjList = Integer.parseInt(st.nextToken());
			
			while (sizeOfAdjList-- > 0) {
				int adjVertex = Integer.parseInt(st.nextToken()) - 1;
				city[i].adjList.add(city[adjVertex]);
			}
		}

		minDiff = Integer.MAX_VALUE;
		generatePowerSet(0, 0, 0, 0);
		System.out.println((minDiff == Integer.MAX_VALUE) ? -1 : minDiff);
	}
	
	private static void generatePowerSet(int isSelected, int idx, int cnt, int sumOfSelected) {
		if (cnt > N >> 1) {
			return;
		}

		if (idx == N) {
			int curDiff = Math.abs(totalNumOfPeople - (sumOfSelected << 1));
			if (minDiff > curDiff && isValid(isSelected)) {
				minDiff = curDiff;
			}

			return;
		}

		generatePowerSet(isSelected | 1 << idx, idx + 1, cnt + 1, sumOfSelected + city[idx].numOfPeople);
		generatePowerSet(isSelected, idx + 1, cnt, sumOfSelected);
	}
	
	private static boolean isValid(int isSelected) {
		if (isSelected == (1 << N) - 1 || isSelected == 0) {
			return false;
		}
		
		List<Area> red = new ArrayList<>();
		List<Area> blue = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if ((isSelected & 1 << i) != 0) {
				red.add(city[i]);
				continue;
			}
			
			blue.add(city[i]);
		}
		
		return isConnected(isSelected, red.get(0), red.size()) && isConnected(isSelected ^ (1 << N) - 1, blue.get(0), blue.size());
	}
	
	private static boolean isConnected(int isSelected, Area start, int sizeOfAdjList) {
		Queue<Area> adjVertices = new ArrayDeque<>();
		boolean[] discovered = new boolean[N];
		
		adjVertices.offer(start);
		discovered[start.label] = true;
		int numOfArea = 1;

		while (!adjVertices.isEmpty()) {
			Area cur = adjVertices.poll();
			
			for (Area next : cur.adjList) {
				if ((isSelected & 1 << next.label) == 0) {
					continue;
				}
				
				if (discovered[next.label]) {
					continue;
				}
				
				adjVertices.offer(next);
				discovered[next.label] = true;
				numOfArea++;
			}
		}
		
		if (numOfArea != sizeOfAdjList) {
			return false;
		}
		
		return true;
	}
	
}