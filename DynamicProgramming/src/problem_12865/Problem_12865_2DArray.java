package problem_12865;

import java.io.*;
import java.util.*;

public class Problem_12865_2DArray {
    private static int[][] objects;
    private static int[][] knapsack;
    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        objects = new int[N + 1][2];

        K = Integer.parseInt(tokenizer.nextToken());
        knapsack = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            objects[i][0] = Integer.parseInt(tokenizer.nextToken());
            objects[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        fillKnapsack();
        System.out.print(knapsack[N][K]);
    }

    private static void fillKnapsack() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++){
                knapsack[i][j] = knapsack[i - 1][j];

                if (objects[i][0] > j) {
                    continue;
                }

                int remainWeight = j - objects[i][0];
                knapsack[i][j] = Math.max(knapsack[i][j], objects[i][1] + knapsack[i - 1][remainWeight]);
            }
        }
    }
}
