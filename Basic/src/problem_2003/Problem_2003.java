package problem_2003;

import java.io.*;
import java.util.*;

// 슬라이딩 윈도우와 투포인터 관련 문제!!
public class Problem_2003 {
	private static int N; // given number size
	private static int[] A; // given number
	private static int M; // target
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		tokenizer = new StringTokenizer(input.readLine());
		A = new int[N + 1];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(tokenizer.nextToken());
		}
		
		System.out.println(getTotalCnt());
	}
	
	private static int getTotalCnt() {
		int totalCnt = 0;
		
		int left = 0;
		int right = 0;
		int sum = 0;

		while (right <= N) {
			if (sum < M) {
				sum += A[right++];
			} else {
				sum -= A[left++];
			}
			
			if (sum == M) {
				totalCnt++;
			}
		}
		
		return totalCnt;
	}
}
