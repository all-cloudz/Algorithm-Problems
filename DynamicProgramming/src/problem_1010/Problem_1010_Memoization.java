package problem_1010;

import java.io.*;
import java.util.*;

public class Problem_1010_Memoization {
	private static final int[][] memoized = new int[30][30];
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(input.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			answer.append(combine(M, N)).append('\n');
		}
		
		System.out.println(answer);
	}
	
	private static int combine(int M, int N) {
		if (memoized[M][N] != 0) {
			return memoized[M][N];
		}
		
		if (M == N || N == 0) {
			return (memoized[M][N] = 1);
		}
		
		memoized[M][N] = combine(M - 1, N - 1) + combine(M - 1, N);
		
		return memoized[M][N];
	}
}
