package implementation.code_숫자가_가장_큰_인접한_곳으로_동시에_이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Code_숫자가_가장_큰_인접한_곳으로_동시에_이동_Sol1 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int n;
    private static int m;
    private static int[][] map;
    private static int t;
    private static Set<Point> marbles;

    public static void main(String[] args) throws IOException {
        init();

        while (t-- > 0) {
            moveAll();
        }

        System.out.println(marbles.size());
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        t = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(input.readLine().split(" "))
                           .mapToInt(Integer::parseInt)
                           .toArray();
        }

        marbles = new HashSet<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            marbles.add(new Point(row, col));
        }
    }

    private static void moveAll() {
        Set<Point> moved = new HashSet<>();
        List<Point> conflicted = new ArrayList<>();

        for (Point marble : marbles) {
            move(marble, moved, conflicted);
        }

        for (Point cur : conflicted) {
            moved.remove(cur);
        }

        marbles = moved;
    }

    private static void move(Point marble, Set<Point> moved, List<Point> conflicted) {
        int max = Integer.MIN_VALUE;
        int argRow = -1;
        int argCol = -1;

        for (int[] dir : DIRECTIONS) {
            int nextRow = marble.row + dir[0];
            int nextCol = marble.col + dir[1];

            if (!inRange(nextRow, nextCol)) {
                continue;
            }

            if (max < map[nextRow][nextCol]) {
                max = map[nextRow][nextCol];
                argRow = nextRow;
                argCol = nextCol;
            }
        }

        Point arg = new Point(argRow, argCol);
        if (moved.contains(arg)) {
            conflicted.add(arg);
        }

        moved.add(arg);
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
        public int hashCode() {
            return Integer.hashCode(this.row) ^ Integer.hashCode(this.col);
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

    }

}
