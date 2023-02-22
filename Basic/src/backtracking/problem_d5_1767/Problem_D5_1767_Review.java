package backtracking.problem_d5_1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class Problem_D5_1767_Review {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int[][] cells;
    private static List<Point> cores;
    private static int maxCount;
    private static int minTotalLength;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            cells = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());

                for (int j = 0; j < N; j++) {
                    cells[i][j] = Integer.parseInt(tokenizer.nextToken());

                    if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
                        continue;
                    }

                    if (cells[i][j] == 1) {
                        cores.add(new Point(i, j));
                    }
                }
            }

            maxCount = Integer.MIN_VALUE;
            minTotalLength = Integer.MAX_VALUE;
            backtracking(0, 0, 0);
            answer.append(String.format("#%d %d%n", tc, minTotalLength));
        }

        System.out.println(answer);
    }

    private static void backtracking(int idx, int count, int totalLength) {
        if (idx == cores.size()) {
            if (maxCount > count) {
                return;
            }

            if (maxCount == count) {
                minTotalLength = Math.min(minTotalLength, totalLength);
                return;
            }

            maxCount = count;
            minTotalLength = totalLength;
            return;
        }

        Point core = cores.get(idx);
        for (int[] dir : DIRECTIONS) {
            installLine(core, dir).ifPresent(length -> backtracking(idx + 1, count + 1, totalLength + length));
            uninstallLine(core, dir);
        }

        backtracking(idx + 1, count, totalLength);
    }

    private static OptionalInt installLine(Point core, int[] dir) {
        int length = 0;
        int nextRow = core.row;
        int nextCol = core.col;

        while (true) {
            nextRow += dir[0];
            nextCol += dir[1];

            if (!isMovable(nextRow, nextCol)) {
                break;
            }

            if (!isInstallable(nextRow, nextCol)) {
                cells[nextRow][nextCol] = 2;
                return OptionalInt.empty();
            }

            cells[nextRow][nextCol] = 1;
            length++;
        }

        return OptionalInt.of(length);
    }

    private static void uninstallLine(Point core, int[] dir) {
        int nextRow = core.row;
        int nextCol = core.col;

        while (true) {
            nextRow += dir[0];
            nextCol += dir[1];

            if (!isMovable(nextRow, nextCol)) {
                break;
            }

            if (cells[nextRow][nextCol] == 2) {
                cells[nextRow][nextCol] = 1;
                break;
            }

            cells[nextRow][nextCol] = 0;
        }
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
    }

    private static boolean isInstallable(int nextRow, int nextCol) {
        return cells[nextRow][nextCol] == 0;
    }

}
