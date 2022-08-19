package problem_1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_1697 {
	private static int N;
	private static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(findTarget());
	}
	
	private static int findTarget() {
		int time = 0;
		
		Queue<Integer> positions = new ArrayDeque<>();
		Set<Integer> discovered = new HashSet<>();
		
		positions.offer(N);
		discovered.add(N);
		
		while (!positions.isEmpty()) {
			int size = positions.size();
			
			while (size-- > 0) {
				int cur = positions.poll();
				
				if (cur == K) {
					return time;
				}
				
				if (!discovered.contains(cur + 1) && cur + 1 <= 100_000) {
					positions.offer(cur + 1);
                    discovered.add(cur + 1);
				}
				
				if (!discovered.contains(cur - 1) && cur - 1 >= 0) {
					positions.offer(cur - 1);
                    discovered.add(cur - 1);
				}
				
				if (!discovered.contains(2 * cur) && 2 * cur <= 100_000) {
					positions.offer(2 * cur);
                    discovered.add(2 * cur);
				}
			}
			
			time++;
		}
		
		throw new NoSuchElementException();
	}
}

