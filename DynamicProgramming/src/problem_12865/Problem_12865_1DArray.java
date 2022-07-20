package problem_12865;

import java.io.*;
import java.util.*;

public class Problem_12865_1DArray {
    private static int[] weights;
    private static int[] values;
    private static int[] knapsack;
    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        weights = new int[N];
        values = new int[N];

        K = Integer.parseInt(tokenizer.nextToken());
        knapsack = new int[K + 1];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            weights[i] = Integer.parseInt(tokenizer.nextToken());
            values[i] = Integer.parseInt(tokenizer.nextToken());
        }

        fillKnapsack();
        System.out.print(knapsack[K]);
    }

    private static void fillKnapsack() {
        for (int i = 0; i < N; i++) {
            for (int j = K; j >= 1; j--){ // 1차원 배열을 이용하면 속도는 빠르지만 이 부분을 주의해야 한다. 1부터 K까지 정방향으로 진행하면 간섭이 생기므로 역방향으로 진행해야 한다.
                if (weights[i] > j) {
                    continue;
                }

                knapsack[j] = Math.max(knapsack[j], values[i] + knapsack[j - weights[i]]);
            }
        }
    }
}
