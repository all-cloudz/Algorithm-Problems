package problem_1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_1520 {
	
	private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	private static int N, M;
	private static int[][] map;
	private static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		cache = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(cache[i], -1);
		}
		
		System.out.println(dfs(N - 1, M - 1));
	}
	
	private static int dfs(int row, int col) {
		if (cache[row][col] != -1) {
			return cache[row][col];
		}
		
		if (row == 0 && col == 0) {
			return cache[row][col] = 1;
		}
		
		int cnt = 0;
		
		for (int[] move : DIRECTIONS) {
			int nextRow = row + move[0];
			int nextCol = col + move[1];
			
			if (!isMovable(nextRow, nextCol)) {
				continue;
			}
			
			if (map[nextRow][nextCol] > map[row][col]) {
				cnt += dfs(nextRow, nextCol);
			}
		}
		
		return cache[row][col] = cnt;
	}
	
	private static boolean isMovable(int nextRow, int nextCol) {
		return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
	}
	
}
