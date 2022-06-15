package problem_11066;

import java.io.*;
import java.util.*;

public class Problem_11066 {
    private static StringBuilder answer = new StringBuilder();

    private static int K;
    private static int[] costs;
    private static long[][] tabulate; // 서로 다른 from, to에 대하여 tabulate[from][to]는 from에서 to까지 문서를 결합하는 데 소요되는 최소 비용이다.
    private static long[] cumulativeSum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(input.readLine());
            costs = new int[K + 1];
            tabulate = new long[K + 1][K + 1];
            cumulativeSum = new long[K + 1];

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
        for (int from = K - 1; from >= 1; from--) {
            for (int to = from + 1; to <= K; to++) {
                tabulate[from][to] = Integer.MAX_VALUE;

                for (int waypoint = from; waypoint < to; waypoint++) {
                    tabulate[from][to] = Math.min(tabulate[from][to], tabulate[from][waypoint] + tabulate[waypoint + 1][to] + sum(from, to)) ;
                }
            }
        }
    }

    private static long sum(int from, int to) {
        return cumulativeSum[to] - cumulativeSum[from - 1];
    }
}
