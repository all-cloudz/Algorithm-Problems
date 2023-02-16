package problem_d4_1868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Problem_D4_1868 {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

    private static int N;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            board = new char[N][N];

            for (int i = 0; i < N; i++) {
                board[i] = input.readLine().toCharArray();
            }

            answer.append(String.format("#%d %d%n", tc, countClick()));
        }

        System.out.println(answer);
    }

    private static int countClick() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != '.') {
                    continue;
                }

                if (isZero(i, j)) {
                    click(i, j);
                    count++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {
                    count++;
                }
            }
        }

        return count;
    }

    private static void click(int row, int col) {
        Queue<Point> points = new ArrayDeque<>();

        points.add(new Point(row, col));
        board[row][col] = 'c';

        while (!points.isEmpty()) {
            Point cur = points.poll();

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (!isMovable(nextRow, nextCol) || board[nextRow][nextCol] != '.') {
                    continue;
                }

                board[nextRow][nextCol] = 'c';
                if (isZero(nextRow, nextCol)) {
                    points.add(new Point(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean isZero(int row, int col) {
        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (!isMovable(nextRow, nextCol)) {
                continue;
            }

            if (board[nextRow][nextCol] == '*') {
                return false;
            }
        }

        return true;
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
    }

}
