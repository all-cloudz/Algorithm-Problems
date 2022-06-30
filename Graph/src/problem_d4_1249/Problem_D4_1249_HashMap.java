package problem_d4_1249;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Problem_D4_1249_HashMap {
    private static class Vertex implements Comparable<Vertex> {
        private int row;
        private int col;
        private int weight;

        public Vertex(int row, int col) {
            this(row, col, 0);
        }

        public Vertex(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || !(o instanceof Vertex)) {
                return false;
            }

            Vertex that = (Vertex) o;
            return (this.row == that.row) && (this.col == that.col);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + this.row;
            hash = hash * 31 + this.col;

            return hash;
        }
    }

    private static final int INF = Integer.MAX_VALUE;
    private static int[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int i = 1; i <= T; i++) {
            final int N = Integer.parseInt(input.readLine());
            adjMatrix = new int[N][N];

            for (int row = 0; row < N; row++) {
                char[] times = input.readLine().toCharArray();

                for (int col = 0; col < N; col++) {
                    adjMatrix[row][col] = times[col] - '0';
                }
            }

            answer.append(String.format("#%d %d%n", i, findMinCost()));
        }

        System.out.print(answer);
    }

    private static int findMinCost() {
        final int N = adjMatrix.length;

        HashMap<Vertex, Integer> dists = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dists.put(new Vertex(i, j), INF);
            }
        }
        dists.put(new Vertex(0, 0), INF);

        PriorityQueue<Vertex> vertices = new PriorityQueue<>();
        vertices.add(new Vertex(0, 0, 0));

        while (!vertices.isEmpty()) {
            Vertex cur = vertices.poll();

            if (cur.weight > dists.get(cur)) {
                continue;
            }

            for (int i = -1; i <= 1; i++) {
                if (cur.row + i < 0 || cur.row + i >= N) {
                    continue;
                }

                for (int j = -1; j <= 1; j++) {
                    if (cur.col + j < 0 || cur.col + j >= N) {
                        continue;
                    }

                    if (i * j == 1 || i * j == -1) {
                        continue;
                    }

                    Vertex next = new Vertex(cur.row + i, cur.col + j, adjMatrix[cur.row + i][cur.col + j]);

                    int minDist = dists.get(next);
                    int newDist = cur.weight + next.weight;

                    if (minDist > newDist) {
                        vertices.add(new Vertex(cur.row + i, cur.col + j, newDist));
                        dists.put(next, newDist);
                    }
                }
            }
        }

        return dists.get(new Vertex(N - 1, N - 1));
    }
}
