package backtracking.problem_15686;

import java.io.*;
import java.util.*;

public class Problem_15686 {
	private static class Point {
		private int row;
		private int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public static int getManhattanDistance(Point p1, Point p2) {
			return Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
		}
	}
	
	private static int N;
	private static int M;
	private static List<Point> houses;
	private static List<Point> chickens;
	private static Point[] selected;
	private static int minDist = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				if (tmp == 1) {
					houses.add(new Point(i, j));
					continue;
				}
				
				if (tmp == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}
		
		selected = new Point[M];
		combine(0, 0);
		System.out.println(minDist);
	}
	
	private static void combine(int start, int cnt) {
		if (cnt == M) {
			minDist = Math.min(minDist, getSumDistances());
			return;
		}
		
		for (int i = start; i < chickens.size(); i++) {
			selected[cnt] = chickens.get(i);
			combine(i + 1, cnt + 1);
		}
	}
	
	private static int getSumDistances() {
		int sumDists = 0;
		
		for (int i = 0; i < houses.size(); i++ ) {
			Point curHouse = houses.get(i);
			
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
				min = Math.min(min, Point.getManhattanDistance(curHouse, selected[j]));
			}
			
			sumDists += min;
		}
		
		return sumDists;
	}
}
