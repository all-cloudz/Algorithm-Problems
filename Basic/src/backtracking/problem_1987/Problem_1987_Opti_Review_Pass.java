package backtracking.problem_1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_1987_Opti_Review_Pass {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int R;
    private static int C;
    private static char[][] board;
    private static int[][] cache;

    public static void main(final String[] args) throws IOException {
        init();
        System.out.println(findLongestDistance(new Point(0, 0), 1 << (board[0][0] - 'A')));
    }

    private static void init() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = input.readLine().toCharArray();
        }

        cache = new int[R][C];
    }

    private static int findLongestDistance(final Point cur, final int used) {
        if (cache[cur.row][cur.col] == used) {
            return Integer.MIN_VALUE;
        }

        int max = Integer.MIN_VALUE;

        for (final int[] dir : DIRECTIONS) {
            final int nextRow = cur.row + dir[0];
            final int nextCol = cur.col + dir[1];

            if (canMove(nextRow, nextCol, used)) {
                max = Math.max(
                        max,
                        findLongestDistance(
                                new Point(nextRow, nextCol),
                                used | 1 << (board[nextRow][nextCol] - 'A')
                        ) + 1
                );
            }
        }

        cache[cur.row][cur.col] = used;
        return maxOrDefault(max);
    }

    private static boolean canMove(final int nextRow, final int nextCol, final int used) {
        return isInRange(nextRow, nextCol) && isNewAlphabet(nextRow, nextCol, used);
    }

    private static boolean isInRange(final int nextRow, final int nextCol) {
        return 0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C;
    }

    private static boolean isNewAlphabet(final int nextRow, final int nextCol, final int used) {
        return (used >> (board[nextRow][nextCol] - 'A') & 1) == 0;
    }

    private static int maxOrDefault(final int max) {
        return (max == Integer.MIN_VALUE) ? 1 : max;
    }

    private static class Point {
        private final int row;
        private final int col;

        public Point(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }
}
