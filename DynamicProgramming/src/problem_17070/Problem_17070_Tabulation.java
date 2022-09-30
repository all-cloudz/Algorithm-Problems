package problem_17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_17070_Tabulation {

	private static int N;
	private static int[][] map;
	private static int[][][] tabulated;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(input.readLine());
		map = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(input.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		tabulated = new int[N + 1][N + 1][3];
		tabulate();
		System.out.println(tabulated[N][N][0] + tabulated[N][N][1] + tabulated[N][N][2]);
	}
	
	private static void tabulate() {
		for (int j = 2; j <= N; j++) {
			if (map[1][j] == 1) {
				break;
			}
			
			tabulated[1][j][0] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0) {
					tabulated[i][j][0] = tabulated[i][j - 1][0] + tabulated[i][j - 1][1];
					
					if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
						tabulated[i][j][1] = tabulated[i - 1][j - 1][0] + tabulated[i - 1][j - 1][1] + tabulated[i - 1][j - 1][2];
					}
					
					tabulated[i][j][2] = tabulated[i - 1][j][1] + tabulated[i - 1][j][2];
				}
			}
		}
	}

}
