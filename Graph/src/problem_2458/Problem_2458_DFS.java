package problem_2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_2458_DFS {

    private static int N;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            int M = Integer.parseInt(input.readLine());
            graph = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i][0] = -1;
            }

            while (M-- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                int i = Integer.parseInt(tokenizer.nextToken());
                int j = Integer.parseInt(tokenizer.nextToken());
                graph[i][j] = 1;
            }

            for (int i = 1; i <= N; i++) {
                dfs(i);
            }

            for (int j = 1; j <= N; j++) {
                for (int i = 1; i <= N; i++) {
                    graph[0][j] += graph[i][j];
                }
            }

            int answerCnt = 0;

            for (int i = 1; i <= N; i++) {
                if (graph[i][0] + graph[0][i] == N - 1) {
                    answerCnt++;
                }
            }

            answer.append(String.format("#%d %d%n", tc, answerCnt));
        }

        System.out.println(answer);
    }

    private static int dfs(int cur) {
        if (graph[cur][0] != -1) {
            return graph[cur][0];
        }

        graph[cur][0] = 0;

        for (int i = 1; i <= N; i++) {
            if (graph[cur][i] != 1) {
                continue;
            }

            if (graph[i][0] == -1) {
                dfs(i);
            }

            if (graph[i][0] == 0) {
                continue;
            }

            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == 1) {
                    graph[cur][j] = 1;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (graph[cur][i] == 1) {
                graph[cur][0]++;
            }
        }

        return graph[cur][0];
    }

}
