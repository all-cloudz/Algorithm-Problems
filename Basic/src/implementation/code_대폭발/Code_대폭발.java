package implementation.code_대폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Code_대폭발 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int n;
    private static int time;
    private static List<Point> bombs;
    private static Set<Point> installed;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        time = 0;
        bombs = new ArrayList<>();
        installed = new HashSet<>();

        int m = Integer.parseInt(tokenizer.nextToken());
        int row = Integer.parseInt(tokenizer.nextToken()) - 1;
        int col = Integer.parseInt(tokenizer.nextToken()) - 1;

        Point start = new Point(row, col);
        bombs.add(start);
        installed.add(start);

        while (time < m) {
            int dist = 1 << time++;

            for (int size = bombs.size(), i = 0; i < size; i++) {
                Point cur = bombs.get(i);
                installBombs(cur, dist);
            }
        }

        System.out.println(bombs.size());
    }

    private static void installBombs(Point cur, int dist) {
        for (int[] dir : DIRECTIONS) {
            int nextRow = cur.row + dir[0] * dist;
            int nextCol = cur.col + dir[1] * dist;
            Point next = new Point(nextRow, nextCol);

            if (!inRange(nextRow, nextCol) || installed.contains(next)) {
                continue;
            }

            bombs.add(next);
            installed.add(next);
        }
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Point)) {
                return false;
            }

            Point that = (Point) o;
            return this.row == that.row
                    && this.col == that.col;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(this.row) ^ Long.hashCode(this.col);
        }
    }

}
