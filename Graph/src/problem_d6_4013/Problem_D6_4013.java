package problem_d6_4013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D6_4013 {
	
	private static int[][] magnets;
	private static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(input.readLine());
			magnets = new int[4][8];
			
			for (int i = 0; i < 4; i++) {
				StringTokenizer tokenizer = new StringTokenizer(input.readLine());
				
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(tokenizer.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				StringTokenizer tokenizer = new StringTokenizer(input.readLine());
				int magnet = Integer.parseInt(tokenizer.nextToken()) - 1;
				int dir = Integer.parseInt(tokenizer.nextToken());
				
				rotate(magnet, dir);
			}
			
			answer.append(String.format("#%d %d%n", tc, score()));
		}
		
		System.out.println(answer);
	}
	
	private static void rotate(int magnet, int dir) {
		boolean[] visited = new boolean[4];
		
		if (dir == 1) {
			clockwise(magnet, visited);
			return;
		}
		
		counterclockwise(magnet, visited);
	}

	private static void clockwise(int magnet, boolean[] visited) {
		visited[magnet] = true;
		
		if (magnet < 0 || magnet >= 4) {
			return;
		}
		
		if (magnet > 0 && !visited[magnet - 1] && magnets[magnet - 1][2] != magnets[magnet][6]) {
			counterclockwise(magnet - 1, visited);
		}
		
		if (magnet < 3 && !visited[magnet + 1] && magnets[magnet][2] != magnets[magnet + 1][6]) {
			counterclockwise(magnet + 1, visited);
		}
		
		int tmp = magnets[magnet][0];
		
		for (int i = 7; i > 0; i--) {
			int idx = (i + 1) % 8;
			magnets[magnet][idx] = magnets[magnet][i];
		}
		
		magnets[magnet][1] = tmp;
	}

	private static void counterclockwise(int magnet, boolean[] visited) {
		visited[magnet] = true;
		
		if (magnet < 0 || magnet >= 4) {
			return;
		}
		
		if (magnet > 0 && !visited[magnet - 1] && magnets[magnet - 1][2] != magnets[magnet][6]) {
			clockwise(magnet - 1, visited);
		}
		
		if (magnet < 3 && !visited[magnet + 1] && magnets[magnet][2] != magnets[magnet + 1][6]) {
			clockwise(magnet + 1, visited);
		}
		
		int tmp = magnets[magnet][0];
		
		for (int i = 1; i < 8; i++) {
			int idx = (i + 7) % 8;
			magnets[magnet][idx] = magnets[magnet][i];
		}
		
		magnets[magnet][7] = tmp;
	}
	
	private static int score() {
		int score = 0;
		
		for (int i = 0; i < 4; i++) {
			int isN = magnets[i][0];
			
			if (isN == 0) {
				continue;
			}
			
			score += Math.pow(2, i);
		}
		
		return score;
	}
	
	
}
