package implementation.problem_2563;

import java.io.*;
import java.util.*;

public class Problem_2563 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		boolean[][] paper = new boolean[101][101];
		int area = 0;
		
		int N = Integer.parseInt(input.readLine());
		while (N-- > 0) {
//			int[] point = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] point = new int[2];
			StringTokenizer st = new StringTokenizer(input.readLine());
			point[0] = Integer.parseInt(st.nextToken());
			point[1] = Integer.parseInt(st.nextToken());

			for (int row = point[0]; row < point[0] + 10; row++) {
				for (int col = point[1]; col < point[1] + 10; col++) {
					if (paper[row][col]) {
						continue;
					}
					
					paper[row][col] = true;
					area++;
				}
			}
		}
		
		System.out.println(area);
	}
}
