package problem_2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_2458_FloydWarshall {

    private static int N;
    private static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            graph = new boolean[N][N];

            int M = Integer.parseInt(input.readLine());
            for (int i = 0; i < M; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                int from = Integer.parseInt(tokenizer.nextToken()) - 1;
                int to = Integer.parseInt(tokenizer.nextToken()) - 1;
                graph[from][to] = true;
            }

            compareAll();

            int answerCnt = 0;
            for (int i = 0; i < N; i++) {
                int count = 0;

                for (int j = 0; j < N; j++) {
                    if (graph[i][j]) {
                        count++;
                    }
                }

                for (int j = 0; j < N; j++) {
                    if (graph[j][i]) {
                        count++;
                    }
                }

                if (count == N - 1) {
                    answerCnt++;
                }
            }

            answer.append(String.format("#%d %d%n", tc, answerCnt));
        }

        System.out.println(answer);
    }

    private static void compareAll() {
        for (int wayPoint = 0; wayPoint < N; wayPoint++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][wayPoint] && graph[wayPoint][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }
    }

}
