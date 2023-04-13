package problem_1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_1939 {

    private static int N;
    private static int M;
    private static DisjointSet disjointSet;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        disjointSet = new DisjointSet(N);

        M = Integer.parseInt(tokenizer.nextToken());
        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            edges.add(new Edge(A, B, C));
        }

        tokenizer = new StringTokenizer(input.readLine());
        int from = Integer.parseInt(tokenizer.nextToken());
        int to = Integer.parseInt(tokenizer.nextToken());
        System.out.println(getMaxWeight(from, to));
    }

    private static long getMaxWeight(int from, int to) {
        Collections.sort(edges);

        int min = Integer.MAX_VALUE;
        for (Edge edge : edges) {
            disjointSet.union(edge.from, edge.to);
            min = Math.min(min, edge.weight);

            if (disjointSet.find(from) == disjointSet.find(to)) {
                break;
            }
        }

        return min;
    }

    private static class DisjointSet {
        private int[] parents;

        public DisjointSet(int N) {
            parents = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }
        }

        public int find(int cur) {
            if (parents[cur] == cur) {
                return cur;
            }

            return parents[cur] = find(parents[cur]);
        }

        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return false;
            }

            parents[rootA] = rootB;
            return true;
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight != o.weight) {
                return o.weight - this.weight;
            }

            if (this.from != o.from) {
                return this.from - o.from;
            }

            return this.to - o.to;
        }
    }

}
