package problem_1992;

import java.io.*;
import java.util.*;

public class Problem_1992 {
	private static int N;
	private static int[][] quadTree;
	private static StringBuilder answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();
		
		N = Integer.parseInt(input.readLine());
		quadTree = new int[N][N];
		for (int i = 0; i < N; i++) {
			quadTree[i] = input.readLine().chars().map(c -> c -'0').toArray();
		}
		
		zip(0, 0, N);
		System.out.println(answer);
	}
	
	private static void zip(int row, int col, int size) {
		if (size == 1) {
			answer.append(quadTree[row][col]);
			return;
		}
		
		int sum = sum(row, col, size);
		
		if (sum == Math.pow(size, 2)) {
			answer.append(1);
			return;
		}
		
		if (sum == 0) {
			answer.append(0);
			return;
		}
		
		answer.append("(");
		
		int newSize = size / 2;
		zip(row, col, newSize);
		zip(row, col + newSize, newSize);
		zip(row + newSize, col, newSize);
		zip(row + newSize, col + newSize, newSize);
		
		answer.append(")");
	}
	
	private static int sum(int row, int col, int size) {
		int sum = 0;
		
		for (int rowSize = row + size, i = row; i < rowSize; i++) {
			for (int colSize = col + size, j = col; j < colSize; j++) {
				sum += quadTree[i][j];
			}
		}
		
		return sum;
	}
}
