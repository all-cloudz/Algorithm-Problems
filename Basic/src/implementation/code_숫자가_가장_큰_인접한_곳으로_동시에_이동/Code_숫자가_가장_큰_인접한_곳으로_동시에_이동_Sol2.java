package implementation.code_숫자가_가장_큰_인접한_곳으로_동시에_이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Code_숫자가_가장_큰_인접한_곳으로_동시에_이동_Sol2 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] count;
    private static int[][] nextCount;
    private static int t;

    public static void main(String[] args) throws IOException {
        init();

        while (t-- > 0) {
            moveAll();
            removeConflicted();
        }

        System.out.println(
                Arrays.stream(count)
                      .flatMapToInt(Arrays::stream)
                      .filter(i -> i != 0)
                      .count()
        );
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

        count = new int[n][n];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            count[row][col]++;
        }

        nextCount = new int[n][n];
    }

    private static void moveAll() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(nextCount[i], 0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (count[i][j] != 0) {
                    move(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            count[i] = nextCount[i].clone();
        }
    }

    private static void move(int row, int col) {
        int max = Integer.MIN_VALUE;
        int argRow = -1;
        int argCol = -1;

        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (!inRange(nextRow, nextCol)) {
                continue;
            }

            if (max < map[nextRow][nextCol]) {
                max = map[nextRow][nextCol];
                argRow = nextRow;
                argCol = nextCol;
            }
        }

        nextCount[argRow][argCol]++;
    }

    private static void removeConflicted() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (count[i][j] > 1) {
                    count[i][j] = 0;
                }
            }
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
