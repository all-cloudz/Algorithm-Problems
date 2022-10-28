package twopointer.problem_1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1644 {

	private static int N;
	private static boolean[] isPrime;
	private static List<Integer> primes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(input.readLine());
		setPrimes();
		
		if (primes.size() == 0) {
			System.out.println(0);
			return;
		}
		
		int answer = countEquals(0, 1);
		System.out.println(answer);
	}
	
	private static void setPrimes() {
		isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		for (int size = (int) Math.sqrt(N), i = 2; i <= size; i++) {
			if (isPrime[i]) {
				update(isPrime, i);
			}
		}
		
		primes = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			if (isPrime[i]) {
				primes.add(i);
			}
		}
	}
	
	private static void update(boolean[] isPrime, int prime) {
		for (int i = 2 * prime; i <= N; i += prime) {
			isPrime[i] = false;
		}
	}
	
	private static int countEquals(int left, int right) {
		int size = primes.size();
		int sum = primes.get(left);
		int count = 0;
		
		while (right <= size) {
			if (sum == N) {
				count++;
			}
			
			if (sum >= N) {
				sum -= primes.get(left++);
				continue;
			}
			
			if (right == size) {
				break;
			}
			
			sum += primes.get(right++);
		}
		
		return count;
	}
	
}
