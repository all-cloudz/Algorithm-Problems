package problem_11047;

import java.io.*;
import java.util.*;

public class Problem_11047 {
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        final int K = Integer.parseInt(tokenizer.nextToken());

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(input.readLine());
        }

        cntCoin(coins, K);

        System.out.println(answer);
    }

    private static void cntCoin(int[] coins, int target) {
        int startIdx = binarySearch_UBD(coins, target);

        while (target != 0) {
            int coin = coins[startIdx--];
            answer += target / coin;
            target = target % coin;
        }
    }

    private static int binarySearch_UBD(int[] coins, int target) {
        int left = 0;
        int right = coins.length;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (coins[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }
}
