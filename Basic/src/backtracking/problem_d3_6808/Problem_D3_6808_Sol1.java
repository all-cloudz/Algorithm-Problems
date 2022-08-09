package backtracking.problem_d3_6808;

import java.io.*;
import java.util.*;

public class Problem_D3_6808_Sol1 {
	private static int[] gyu;
	private static int[] nums;
	private static int[] selected;
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
			selected = new int[9];
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
			
			permutate(0);
			answer.append(String.format("#%d %d %d%n", tc, winCnt, loseCnt));
		}
		
		System.out.println(answer);
	}
	
	public static void permutate(int cnt) {
		if (cnt == 9) {
			playGame();
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (isSelected[i]) {
				continue;
			}
			
			selected[cnt] = nums[i];
			isSelected[i] = true;
			permutate(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	private static void playGame() {
		int sumOfGyu = 0;
		int sumOfIn = 0;
		
		for (int i = 0; i < 9; i++) {
			int cardOfGyu = gyu[i];
			int cardOfIn = selected[i];
			
			if (cardOfGyu > cardOfIn) {
				sumOfGyu += cardOfGyu + cardOfIn;
				continue;
			}
			
			if (cardOfGyu < cardOfIn) {
				sumOfIn += cardOfGyu + cardOfIn;
			}
		}
		
		if (sumOfGyu > sumOfIn) {
			winCnt++;
			return;
		}
		
		if (sumOfGyu < sumOfIn) {
			loseCnt++;
		}
	}
}
