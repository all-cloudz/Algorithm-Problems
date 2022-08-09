package implementation.problem_2563;

import java.io.*;
import java.util.*;

public class Problem_2563_Sol2 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		int[][] paper = new int[101][101];
		
		int N = Integer.parseInt(input.readLine());
		while (N-- > 0) {
			int[] point = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int row = point[0]; row < point[0] + 10; row++) {
				for (int col = point[1]; col < point[1] + 10; col++) {
					paper[row][col] = 1;
				}
			}
		}
		
//		int area = 0;
//		for (int i = 1; i <= 100; i++) {
//			area += Arrays.stream(paper[i]).sum();
//		}
		
		int area = Arrays.stream(paper).flatMapToInt(Arrays::stream).sum();		
		System.out.println(area);
	}
}
