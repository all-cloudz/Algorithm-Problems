package problem_13974;

import java.io.*;
import java.util.*;

// K가 5,000개 까지 있고, 시간복잡도가 O(N^3)이므로 6초안에 1개의 쿼리도 처리할 수 없다. 최소 250초는 필요하다.
// 시간복잡도가 O(N^2)이 되도록 처리할 수 있는 방법이 있다면 시간 안에 충분히 해결할 수 있고, 이를 위해 크누스 최적화가 필요하다.
public class Problem_13974_Fail {
    private static int K;
    private static int[] costs;
    private static long[] cumulativeSum;
    private static long[][] tabulate;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            K = Integer.parseInt(input.readLine());
            cumulativeSum = new long[K + 1];
            tabulate = new long[K + 1][K + 1];

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int i = 1; i <= K; i++) {
                cumulativeSum[i] = cumulativeSum[i - 1] + Integer.parseInt(tokenizer.nextToken());
            }

            fillTable();
            answer.append(tabulate[1][K]).append('\n');
        }

        System.out.println(answer);
    }

    private static void fillTable() {
        for (int from = K - 1; from > 0; from--) {
            for (int to = from + 1; to <= K; to++) {
                tabulate[from][to] = Integer.MAX_VALUE;

                for (int waypoint = from; waypoint < to; waypoint++) {
                    tabulate[from][to] = Math.min(tabulate[from][to], tabulate[from][waypoint] + tabulate[waypoint + 1][to] + sum(from, to));
                }
            }
        }
    }

    private static long sum(int from, int to) {
        return cumulativeSum[to] - cumulativeSum[from - 1];
    }
}
