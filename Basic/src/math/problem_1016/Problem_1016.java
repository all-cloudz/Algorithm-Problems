package math.problem_1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_1016 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		long min = Long.parseLong(tokenizer.nextToken());
		long max = Long.parseLong(tokenizer.nextToken());
		
		boolean[] checker = new boolean[(int) (max - min + 1)]; 
		
		for (long i = 2; i * i <= max; i++) {
			long square = i * i;
			long multiple = min / square;
			
			if (min % square != 0) {
				multiple++;
			}
			
			multiple *= square;

			while (multiple <= max) {
				checker[(int) (multiple - min)] = true;
				multiple += square;
			}
		}
		
		int answer = 0;
		
		for (boolean cur : checker) {
			if (!cur) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
}
