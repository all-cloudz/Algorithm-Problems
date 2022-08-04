package problem_1929;

import java.io.*;
import java.util.*;

// Stream을 사용해서 느리기도 하지만, Stream을 사용하면 각 요소에 대해 소수 판정을 하므로 느릴 수 밖에 없다.
public class Problem_1929_Sol2 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		final int M = Integer.parseInt(tokenizer.nextToken());
		final int N = Integer.parseInt(tokenizer.nextToken());
		
		List<Integer> numList = new ArrayList<>();
		for (int i = M; i <= N; i++) {
			numList.add(i);
		}
		
		numList.stream().filter(s -> {
			if (s == 1) {
				return false;
			}
			
			for (int i = 2; i <= Math.sqrt(s); i++) {
				if (s % i == 0) {
					return false;
				}
			}
			
			return true;
		}).forEach(System.out::println);
	}
}
