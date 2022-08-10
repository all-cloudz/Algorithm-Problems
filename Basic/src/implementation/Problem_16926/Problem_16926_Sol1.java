package implementation.Problem_16926;

import java.io.*;
import java.util.*;

public class Problem_16926_Sol1 {
	private static int N;
	private static int M;
	private static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		final int R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotate(R);
		
//		for (int i = 0; i < N; i++) {
//			answer.append(Arrays.toString(arr[i]).replaceAll("\\[|\\]|\\,", "")).append('\n');
//		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				answer.append(arr[i][j]).append(' ');
			}
			
			answer.append('\n');
		}
		
		System.out.println(answer);
	}
	
	private static void rotate(int cnt) {
		final int[][] DIRECTION = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int rotateCnt = Math.min(N, M) / 2;	
		
		while (cnt-- > 0) {
			for (int i = 0; i < rotateCnt; i++) {
				int curRow = i;
				int curCol = i;
				
				int tmp = arr[curRow][curCol];
				
				int index = 0;
				while (index < 4) {
					int prevRow = curRow + DIRECTION[index][0];
					int prevCol = curCol + DIRECTION[index][1];
					
					if (!isExist(prevRow, prevCol, i)) {
						index++;
						continue;
					}
					
					arr[curRow][curCol] = arr[prevRow][prevCol];
					curRow = prevRow;
					curCol = prevCol;
				}
				
				arr[i + 1][i] = tmp;
			}
		}
	}
	
	private static boolean isExist(int row, int col, int limit) {
		return limit <= row && row < N - limit && limit <= col && col < M - limit;
	}
}
