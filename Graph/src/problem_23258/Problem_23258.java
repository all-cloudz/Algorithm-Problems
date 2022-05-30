package problem_23258;

import java.io.*;
import java.util.*;

public class Problem_23258 {
    private static final int INF = 170324 * 300 + 1;

    private static int[][][] dists;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int N = Integer.parseInt(tokenizer.nextToken());
        final int Q = Integer.parseInt(tokenizer.nextToken());

        dists = new int[N + 1][N + 1][N + 1];
        /* 순서쌍 (i, j, k)에서 i는 depart, j는 arrive를 의미하고, k는 waypoint로 k 이하의 값만 경유했다는 것을 의미한다.
         * 즉, 플로이드-워셜 알고리즘에서 각 경유지를 거쳐간 결과를 덮어씌우지 않고 모두 기록한 것이다.
         * 따라서 C보다 작은 값만을 경유하여 depart에서 arrive로 이동한 거리는 dists[depart][arrive][C - 1]이다. */
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 1; j <= N; j++) {
                int weight = Integer.parseInt(tokenizer.nextToken());

                if (weight == 0 && i != j) {
                    dists[i][j][0] = INF;
                    continue;
                }

                dists[i][j][0] = weight;
            }
        }

        floydWarshall();

        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            int c = Integer.parseInt(tokenizer.nextToken());
            int depart = Integer.parseInt(tokenizer.nextToken());
            int arrive = Integer.parseInt(tokenizer.nextToken());

            if (dists[depart][arrive][c - 1] == INF) {
                answer.append(-1).append('\n');
                continue;
            }

            answer.append(dists[depart][arrive][c - 1]).append('\n');
        }

        System.out.print(answer);
    }

    private static void floydWarshall() {
        int size = dists.length - 1;

        for (int waypoint = 1; waypoint <= size; waypoint++) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    dists[i][j][waypoint] = Math.min(dists[i][j][waypoint - 1], dists[i][waypoint][waypoint - 1] + dists[waypoint][j][waypoint - 1]);
                }
            }
        }
    }
}
