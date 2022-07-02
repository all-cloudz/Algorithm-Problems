package problem_d4_1226;

import java.io.*;
import java.util.*;

public class Problem_D4_1226_HashSet {
    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (o == null || !(o instanceof Point)) {
                return false;
            }

            Point that = (Point) o;

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

    private static StringBuilder answer = new StringBuilder();
    private static int[][] maze = new int[16][16];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {
            input.readLine();

            for (int j = 0; j < 16; j++) {
                maze[j] = Arrays.stream(input.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }

            if (findPath()) {
                answer.append(String.format("#%d %d%n", i, 1));
                continue;
            }

            answer.append(String.format("#%d %d%n", i, 0));
        }

        System.out.print(answer);
    }

    private static boolean findPath() {
        Point start = new Point(1, 1);

        Queue<Point> points = new LinkedList<>();
        HashSet<Point> discovered = new HashSet<>();

        points.offer(start);
        discovered.add(start);

        while (!points.isEmpty()) {
            Point cur = points.poll();

            if (maze[cur.row][cur.col] == 3) {
                return true;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    if (i * j == -1 || i * j == 1) {
                        continue;
                    }

                    Point next = new Point(cur.row + i, cur.col + j);

                    if (maze[next.row][next.col] == 1) {
                        continue;
                    }

                    if (!discovered.contains(next)) {
                        points.offer(next);
                        discovered.add(next);
                    }
                }
            }
        }

        return false;
    }
}
