package problem_17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_17070_DFS {
	
	private static int N;
	private static int[][] map;
	private static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(input.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		dfs(0, 1, 0);
		System.out.println(cnt);
	}
	
	private static void dfs(int row, int col, int direction) {
		if (!isMovable(row, col) || map[row][col] == 1) {
			return;
		}
		
		if (direction == 1 && (map[row][col - 1] == 1 || map[row - 1][col] == 1)) {
			return;
		}
		
		if (row == N - 1 && col == N - 1) {
			cnt++;
			return;
		}
		
		// 가로 방향
		if (direction == 0) {
			dfs(row, col + 1, direction);
			dfs(row + 1, col + 1, direction + 1);
			return;
		}
		
		// 대각선 방향
		if (direction == 1) {
			dfs(row, col + 1, direction - 1);
			dfs(row + 1, col + 1, direction);
			dfs(row + 1, col, direction + 1);
			return;
		}
		
		// 세로 방향
		dfs(row + 1, col + 1, direction - 1);
		dfs(row + 1, col, direction);
	}
	
	private static boolean isMovable(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
	
}
