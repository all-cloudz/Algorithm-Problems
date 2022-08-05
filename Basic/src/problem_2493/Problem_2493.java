package problem_2493;

import java.io.*;
import java.util.*;

public class Problem_2493 {
	private static class Tower {
		private int no;
		private int height;
		
		public Tower(int no, int height) {
			this.no = no;
			this.height = height;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int N = Integer.parseInt(input.readLine());
		Stack<Tower> towers = new Stack<>();

		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		loop : for (int i = 1; i <= N; i++) {
			Tower cur = new Tower(i, Integer.parseInt(tokenizer.nextToken()));
			
			while (!towers.isEmpty()) {
				if (cur.height <= towers.peek().height) {
					answer.append(towers.peek().no).append(' ');
					towers.push(cur);
					continue loop;
				}
				
				towers.pop();
			}
			
			answer.append("0 ");
			towers.push(cur);
		}
		
		System.out.println(answer);
	}
}
