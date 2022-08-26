package problem_10971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_10971_DP {
    private static int INF = 10 * 1_000_000 + 1;

    private static int N;
    private static int[][] graph;
    private static int[][] memoized;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        graph = new int[N][N];
        memoized = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(fillTable(1, 0));
    }

    private static int fillTable(int visited, int cur) {
        if ((visited == (1 << N) - 1)) {
            return (graph[cur][0] == 0) ? INF : graph[cur][0];
        }

        if (memoized[visited][cur] != 0) {
            return memoized[visited][cur];
        }

        memoized[visited][cur] = INF;

        for (int i = 0; i < N; i++) {
            if ((visited & 1 << i) != 0) {
                continue;
            }

            if (graph[cur][i] == 0) {
                continue;
            }

            memoized[visited][cur] = Math.min(memoized[visited][cur], fillTable(visited | 1 << i, i) + graph[cur][i]);
        }

        return memoized[visited][cur];
    }
}
