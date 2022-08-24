package problem_7576;

import java.io.*;
import java.util.*;

public class Problem_7576 {
	private static class Tomato {
		private int row;
		private int col;
		
		public Tomato(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	private static int N, M;
	private static int[][] box;
	private static List<Tomato> tomatoList;
	private static int cntOfRotten;
	private static int cntOfRipe;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		box = new int[M][N];
		tomatoList = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				
				
				if (box[i][j] == -1) {
					cntOfRotten++;
					continue;
				}
				
				if (box[i][j] == 1) {
					tomatoList.add(new Tomato(i, j));
				}
			}
		}
		
		if (N * M == tomatoList.size() + cntOfRotten) {
			System.out.println(0);
			return;
		}
		
		System.out.println(spreadTime());
	}
	
	private static int spreadTime() {
		Queue<Tomato> tomatoes = new ArrayDeque<>();
		boolean[][] discovered = new boolean[M][N];
		
		for (Tomato start : tomatoList) {
			tomatoes.offer(start);
			discovered[start.row][start.col] = true;
		}
		
		int time = -1;
		while (!tomatoes.isEmpty()) {
			for (int levelSize = tomatoes.size(), i = 0; i < levelSize; i++) {
				Tomato cur = tomatoes.poll();
				cntOfRipe++;
				
				for (int[] move : DIRECTIONS) {
					int nextRow = cur.row + move[0];
					int nextCol = cur.col + move[1];
					
					if (!isSpreadable(nextRow, nextCol) || box[nextRow][nextCol] == -1) {
						continue;
					}
					
					if (!discovered[nextRow][nextCol] && box[nextRow][nextCol] == 0) {
						tomatoes.offer(new Tomato(nextRow, nextCol));
						box[nextRow][nextCol] = 1;
						discovered[nextRow][nextCol] = true;
					}
				}
			}
			
			time++;
		}
		
		if (!isAllRipe()) {
			return -1;
		}
		
		return time;
	}
	
	private static boolean isSpreadable(int nextRow, int nextCol) {
		return 0 <= nextRow && nextRow < M && 0 <= nextCol && nextCol < N;
	}
	
	private static boolean isAllRipe() {
		return cntOfRipe + cntOfRotten == N * M;
	}
}
