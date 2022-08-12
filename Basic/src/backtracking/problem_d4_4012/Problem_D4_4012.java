package backtracking.problem_d4_4012;

import java.io.*;
import java.util.*;

// 임의로 D4 난이도로 두었음, 실제로는 난이도 표기가 안 되어있음
public class Problem_D4_4012 {
	private static int N;
	private static int[] foodA;
	private static int[] foodB;
	private static int[][] synergy;
	private static int totalMin;
	private static int finish;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(input.readLine());
			foodA = new int[N >> 1];
			foodB = new int[N >> 1];
			synergy = new int[N + 1][N + 1];
			totalMin = Integer.MAX_VALUE;
			
			for (int i = N; i >= N / 2; i--) {
				finish = finish | (1 << i);
			}
			
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(input.readLine());
				
				for (int j = 1; j <= N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			setFood(1, 0, 0);
			answer.append(String.format("#%d %d%n", tc, totalMin));
		}
		
		System.out.println(answer);
	}
	
	private static void setFood(int start, int cnt, int isIngredientOfA) {
		if (cnt == N >> 1) {
			setFoodB(isIngredientOfA);
			totalMin = Math.min(totalMin, getSynergyDiff());
			return;
		}
		
		for (int i = start; i <= N; i++) {
			foodA[cnt] = i;
			setFood(i + 1, cnt + 1, isIngredientOfA | (1 << i));
		}
	}
	
	
	private static void setFoodB(int isIngredientOfA) {
		int idx = 0;
		
		for (int i = 1; i <= N; i++) {
			if ((isIngredientOfA & (1 << i)) != 0) {
				continue;
			}
			
			foodB[idx++] = i;
		}
	}
	
	private static int getSynergyDiff() {
		int sumOfA = getSynergySum(foodA);
		int sumOfB = getSynergySum(foodB);
		
		return Math.abs(sumOfA - sumOfB);
	}
	
	private static int getSynergySum(int[] food) {
		int sum = 0;
		
		for (int i = 0; i < food.length; i++) {
			for (int j = i + 1; j < food.length; j++) {
				sum += synergy[food[i]][food[j]] + synergy[food[j]][food[i]];
			}
		}
		
		return sum;
	}
}
