package parametric_search.code_색칠된_칸으로만_이동_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Code_색칠된_칸으로만_이동_2 {

    public static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int M;
    private static int[][] board;
    private static Point start;
    private static List<Point> colored;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(getMinDiff());
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(input.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        colored = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                int isColored = Integer.parseInt(tokenizer.nextToken());
                Point point = new Point(i, j);

                if (isColored == 1) {
                    colored.add(new Point(i, j));
                    start = point;
                }
            }
        }
    }

    private static int getMinDiff() {
        int left = 0;
        int right = 1_000_000_001;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (isConnected(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private static boolean isConnected(int diff) {
        Queue<Point> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[N][M];

        points.add(start);
        discovered[start.row][start.col] = true;

        while (!points.isEmpty()) {
            Point cur = points.poll();

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (!inRange(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                if (Math.abs(board[cur.row][cur.col] - board[nextRow][nextCol]) <= diff) {
                    points.add(new Point(nextRow, nextCol));
                    discovered[nextRow][nextCol] = true;
                }
            }
        }

        return isAllVisited(discovered);
    }

    private static boolean isAllVisited(boolean[][] discovered) {
        for (Point cur : colored) {
            if (!discovered[cur.row][cur.col]) {
                return false;
            }
        }

        return true;
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
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
            return (this.row == that.row)
                    && (this.col == that.col);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
    }

}
