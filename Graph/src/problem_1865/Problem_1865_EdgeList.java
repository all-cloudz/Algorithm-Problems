package problem_1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_1865_EdgeList {

    private static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static int N;
    private static int M;
    private static int W;
    private static List<Edge> roads;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            W = Integer.parseInt(tokenizer.nextToken());
            roads = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                int vertex1 = Integer.parseInt(tokenizer.nextToken());
                int vertex2 = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());

                roads.add(new Edge(vertex1, vertex2, weight));
                roads.add(new Edge(vertex2, vertex1, weight));
            }

            for (int i = 0; i < W; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                int from = Integer.parseInt(tokenizer.nextToken());
                int to = Integer.parseInt(tokenizer.nextToken());
                int weight = (-1) * Integer.parseInt(tokenizer.nextToken());

                roads.add(new Edge(from, to, weight));
            }

            if (existNegativeCycle()) {
                answer.append("YES\n");
                continue;
            }

            answer.append("NO\n");
        }

        System.out.print(answer);
    }

    private static boolean existNegativeCycle() {
        int[] dists = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            for (Edge cur : roads) {
                int minDist = dists[cur.to];
                int newDist = dists[cur.from] + cur.weight;

                if (minDist <= newDist) {
                    continue;
                }

                if (i == N) {
                    return true;
                }

                dists[cur.to] = newDist;
            }
        }

        return false;
    }

}
