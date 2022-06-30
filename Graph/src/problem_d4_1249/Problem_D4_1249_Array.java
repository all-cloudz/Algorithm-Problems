package problem_d4_1249;

import java.io.*;
import java.util.*;

public class Problem_D4_1249_Array {
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

        boolean[][] visited = new boolean[N][N];
        int[][] dists = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dists[i], INF);
        }
        dists[0][0] = 0;

        PriorityQueue<Vertex> vertices = new PriorityQueue<>();
        vertices.add(new Vertex(0, 0, 0));

        while (!vertices.isEmpty()) {
            Vertex cur = vertices.poll();

            if (visited[cur.row][cur.col]) {
                continue;
            }

            visited[cur.row][cur.col] = true;

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

                    if (visited[cur.row + i][cur.col + j]) {
                        continue;
                    }

                    int minDist = dists[cur.row + i][cur.col + j];
                    int newDist = cur.weight + adjMatrix[cur.row + i][cur.col + j];

                    if (minDist > newDist) {
                        vertices.add(new Vertex(cur.row + i, cur.col + j, newDist));
                        dists[cur.row + i][cur.col + j] = newDist;
                    }
                }
            }
        }

        return dists[N - 1][N - 1];
    }
}