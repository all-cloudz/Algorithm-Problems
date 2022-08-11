package backtracking.problem_3040;

import java.io.*;
import java.util.Arrays;

public class Problem_3040_Sol1 {
	private static final int IDENTIFIER = 100;
	
	private static int[] nums;
	private static int[] dwarfs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		nums = new int[9];
		dwarfs = new int[7];
		
		for (int i = 0; i < 9; i++) { 
			nums[i] = Integer.parseInt(input.readLine());
		}
		
		setDwarfs(0, 0);
	}
	
	private static void setDwarfs(int start, int cnt) {
		if (cnt == 7) {
			if (Arrays.stream(dwarfs).sum() != IDENTIFIER) {
				return;
			}
			
			Arrays.stream(dwarfs).forEach(System.out::println);
			return;
		}
		
		for (int i = start; i < 9; i++) {
			dwarfs[cnt] = nums[i];
			setDwarfs(i + 1, cnt + 1);
		}
	}
}
