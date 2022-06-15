package problem_11066;

import java.io.*;
import java.util.*;

/* 커누스 최적화 (Knuth Optimization) : 두 배열 DP[][], C[][]가 다음 조건을 만족시킬 때,
 *
 * 1. 점화식 : DP[i][j] = min_{i<k<j} {DP[i][k] + DP[k][j]} + C[i][j]
 * 2. 사각 부등식 : a<=b<=c<=d, C[a][c] + C[b][d] <= C[a][d] + C[b][c]
 * 3. 단조성 : C[a][d] >= C[b][c]
 *
 * 배열 A[][]에 대하여 1에서 DP[i][j]가 최소가 되도록 하는 index k의 값을 A[i][j]라 하면, 즉 A[i][j] = argmin_{i<k<j} {DP[i][k] + DP[k][j]}이면
 *      A[i][j - 1] <= A[i][j] <= A[i + 1][j]
 * 가 성립한다. */

public class Problem_11066_Knuth {
    private static StringBuilder answer = new StringBuilder();

    private static int K;
    private static int[] costs;
    private static long[][] tabulate;
    private static long[] cumulativeSum;
    private static int[][] optIndices;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(input.readLine());
            costs = new int[K + 1];
            tabulate = new long[K + 1][K + 1];
            cumulativeSum = new long[K + 1];
            optIndices = new int[K + 1][K + 1];

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 1; j <= K; j++) {
                costs[j] = Integer.parseInt(tokenizer.nextToken());
                cumulativeSum[j] = cumulativeSum[j - 1] + costs[j];
            }

            combineCost();

            answer.append(tabulate[1][K]).append('\n');
        }

        System.out.print(answer);
    }

    private static void combineCost() {
        for (int i = 1; i <= K; i++) {
            optIndices[i][i] = i;
        }

        for (int from = K - 1; from >= 1; from--) {
            for (int to = from + 1; to <= K; to++) {
                if (tabulate[from][to] == 0) {
                    tabulate[from][to] = Integer.MAX_VALUE;
                }

                for (int optIndex = optIndices[from][to - 1]; optIndex <= optIndices[from + 1][to]; optIndex++) {
                    if (optIndex == K) {
                        break;
                    }

                    long newCost = tabulate[from][optIndex] + tabulate[optIndex + 1][to] + sum(from, to);

                    if (tabulate[from][to] > newCost) {
                        tabulate[from][to] = newCost;
                        optIndices[from][to] = optIndex;
                    }
                }
            }
        }
    }

    private static long sum(int from, int to) {
        return cumulativeSum[to] - cumulativeSum[from - 1];
    }
}
