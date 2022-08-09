package backtracking.problem_d3_6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_6808_Sol2 {
	private static int[] gyu;
	private static int[] nums;
	private static boolean[] isSelected;
	private static int winCnt;
	private static int loseCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			gyu = new int[9];
			nums = new int[9];
			isSelected = new boolean[9];
			winCnt = 0;
			loseCnt = 0;
			
			StringTokenizer st = new StringTokenizer(input.readLine());
			boolean[] hasGyu = new boolean[19];
			for (int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				gyu[i] = num;
				hasGyu[num] = true;
			}
			
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (hasGyu[i]) {
					continue;
				}
				
				nums[idx++] = i; 
			}
			
			playGame(0, 0, 0);
			answer.append(String.format("#%d %d %d%n", tc, winCnt, loseCnt));
		}
		
		System.out.println(answer);
	}
	
	public static void playGame(int cnt, int sumOfGyu, int sumOfIn) {
		if (cnt == 9) {
			if (sumOfGyu > sumOfIn) {
				winCnt++;
				return;
			}
			
			if (sumOfGyu < sumOfIn) {
				loseCnt++;
			}
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (isSelected[i]) {
				continue;
			}
			
			isSelected[i] = true;

			if (gyu[cnt] > nums[i]) {
				playGame(cnt + 1, sumOfGyu + gyu[cnt] + nums[i], sumOfIn);
			} else if (gyu[cnt] < nums[i]) {
				playGame(cnt + 1, sumOfGyu, sumOfIn + gyu[cnt] + nums[i]);
			} else {
				playGame(cnt + 1, sumOfGyu, sumOfIn);
			}
			
			isSelected[i] = false;
		}
	}
}
