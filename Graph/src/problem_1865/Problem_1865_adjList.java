package problem_1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Problem_1865_adjList {

    private static class Vertex {
        private int to;
        private int weight;

        public Vertex(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static int N;
    private static int M;
    private static int W;
    private static Map<Integer, List<Vertex>> roads; // key: from, value: adjList

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            W = Integer.parseInt(tokenizer.nextToken());

            roads = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                roads.put(i, new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                int vertex1 = Integer.parseInt(tokenizer.nextToken());
                int vertex2 = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());

                roads.get(vertex1).add(new Vertex(vertex2, weight));
                roads.get(vertex2).add(new Vertex(vertex1, weight));
            }

            for (int i = 0; i < W; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                int from = Integer.parseInt(tokenizer.nextToken());
                int to = Integer.parseInt(tokenizer.nextToken());
                int weight = (-1) * Integer.parseInt(tokenizer.nextToken());

                roads.get(from).add(new Vertex(to, weight));
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
            for (int j = 1; j <= N; j++) {
                for (Vertex cur : roads.get(j)) {
                    int minDist = dists[cur.to];
                    int newDist = dists[j] + cur.weight;

                    if (minDist <= newDist) {
                        continue;
                    }

                    if (i == N) {
                        return true;
                    }

                    dists[cur.to] = newDist;
                }
            }
        }

        return false;
    }

}
