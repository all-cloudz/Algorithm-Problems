package problem_11404;

import java.io.*;
import java.util.*;

public class Problem_11404 {
    private static class Graph {
        private int[][] adjMatrix;
        private int size;

        public Graph(int size) {
            adjMatrix = new int[size + 1][size + 1];
            this.size = size;
        }
    }

    private static final int INF = 100000 * 100 + 1; // 다익스트라와 같이 INF = Math.MAX_VALUE로 하면 덧셈 연산을 할 때 오버플로우가 발생한다. 따라서 문제에서 나올 수 없는 최댓값을 오버플로우가 발생하지 않는 선에서 적절히 선택해야 한다.

    private static StringBuilder answer = new StringBuilder();
    private static int[][] dists;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int n = Integer.parseInt(input.readLine());
        Graph graph = new Graph(n);
        dists = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dists[i], INF);
            dists[i][i] = 0;
        }

        final int m = Integer.parseInt(input.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            graph.adjMatrix[head][tail] = (graph.adjMatrix[head][tail] == 0) ? weight : Math.min(graph.adjMatrix[head][tail], weight);
            dists[head][tail] = graph.adjMatrix[head][tail];
        }

        floydWarshall(graph);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dists[i][j] == INF) {
                    answer.append(0).append(' ');
                    continue;
                }

                answer.append(dists[i][j]).append(' ');
            }

            answer.append('\n');
        }

        System.out.print(answer);
    }

    private static void floydWarshall(Graph graph) {
        for (int waypoint = 1; waypoint <= graph.size; waypoint++) {
            for (int i = 1; i <= graph.size; i++) {
                for (int j = 1; j <= graph.size; j++) {
                    dists[i][j] = Math.min(dists[i][j], dists[i][waypoint] + dists[waypoint][j]);
                }
            }
        }
    }
}