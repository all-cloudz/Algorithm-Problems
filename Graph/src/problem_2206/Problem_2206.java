package problem_2206;

import java.io.*;
import java.util.*;

public class Problem_2206 {
    private static class Point {
        private int row;
        private int col;
        private int dist;
        private boolean destroyed;

        public Point(int row, int col, int dist, boolean destroyed) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.destroyed = destroyed;
        }
    }

    private static final int[][] DIRECTIONS = new int[][] {
            { -1, 0 },
            { 1, 0 },
            { 0, -1 },
            { 0, 1 }
    };

    private static int N;
    private static int M;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = input.readLine().toCharArray();
        }

        System.out.println(findShortestDistance());
    }

    private static int findShortestDistance() {
        Queue<Point> points = new ArrayDeque<>();
        boolean[][][] discovered = new boolean[2][N][M];

        points.offer(new Point(0, 0, 1, false));
        discovered[0][0][0] = true;

        while (!points.isEmpty()) {
            Point cur = points.poll();

            if (cur.row == N - 1 && cur.col == M - 1) {
                return cur.dist;
            }

            for (int[] direction : DIRECTIONS) {
                int nextRow = cur.row + direction[0];
                int nextCol = cur.col + direction[1];

                if (!isMovable(nextRow, nextCol)) {
                    continue;
                }

                boolean isWall = (map[nextRow][nextCol] == '1');
                int destroyLayer = (cur.destroyed) ? 1 : 0;

                if (!isWall && !discovered[destroyLayer][nextRow][nextCol]) {
                    points.offer(new Point(nextRow, nextCol, cur.dist + 1, cur.destroyed));
                    discovered[destroyLayer][nextRow][nextCol] = true;
                    continue;
                }

                if (isWall && !cur.destroyed) {
                    points.offer(new Point(nextRow, nextCol, cur.dist + 1, true));
                    discovered[1][nextRow][nextCol] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
    }
}
