package problem_d4_1249;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem_D4_1249_Review {

    private static class Point implements Comparable<Point> {
        private int row;
        private int col;
        private int restore;

        public Point(int row, int col, int restore) {
            this.row = row;
            this.col = col;
            this.restore = restore;
        }

        @Override
        public int compareTo(Point o) {
            return this.restore - o.restore;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = input.readLine();

                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            answer.append(String.format("#%d %d%n", tc, dijkstra()));
        }

        System.out.println(answer);
    }

    private static int dijkstra() {
        PriorityQueue<Point> open = new PriorityQueue<>();
        int[][] dists = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dists[i], Integer.MAX_VALUE);
        }

        open.offer(new Point(0, 0, 0));
        dists[0][0] = 0;

        while (!open.isEmpty()) {
            Point cur = open.poll();

            if (cur.row == N - 1 && cur.col == N - 1) {
                return dists[N - 1][N - 1];
            }

            if (cur.restore > dists[cur.row][cur.col]) {
                continue;
            }

            for (int[] move : DIRECTIONS) {
                int nextRow = cur.row + move[0];
                int nextCol = cur.col + move[1];

                if (!isMovable(nextRow, nextCol)) {
                    continue;
                }

                int minDist = dists[nextRow][nextCol];
                int newDist = dists[cur.row][cur.col] + map[nextRow][nextCol];

                if (minDist > newDist) {
                    open.offer(new Point(nextRow, nextCol, newDist));
                    dists[nextRow][nextCol] = newDist;
                }
            }
        }

        return -1;
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
    }

}
