package problem_d6_1263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D6_1263 {

    private static final int INF = 1 * 1000 * 1000 + 1;

    private static int N;
    private static int[][] network;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            N = Integer.parseInt(tokenizer.nextToken());
            network = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    network[i][j] = Integer.parseInt(tokenizer.nextToken());

                    if (i != j && network[i][j] == 0) {
                        network[i][j] = INF;
                    }
                }
            }

            floydWarshall();
            answer.append(String.format("#%d %d%n", tc, minSum()));
        }

        System.out.println(answer);
    }

    private static void floydWarshall() {
        for (int wayPoint = 1; wayPoint <= N; wayPoint++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    network[i][j] = Math.min(network[i][j], network[i][wayPoint] + network[wayPoint][j]);
                }
            }
        }
    }

    private static int minSum() {
        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int sum = 0;

            for (int j = 1; j <= N; j++) {
                sum += network[i][j];
            }

            minSum = Math.min(minSum, sum);
        }

        return minSum;
    }

}
