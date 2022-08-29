package backtracking.problem_d5_1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_D5_1767 {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};

    private static int N;
    private static int[][] cell;
    private static List<Point> coreList;
    private static int maxCores;
    private static int minLength;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            cell = new int[N][N];
            coreList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());

                for (int j = 0; j < N; j++) {
                    cell[i][j] = Integer.parseInt(tokenizer.nextToken());

                    if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
                        continue;
                    }

                    if (cell[i][j] == 1) {
                        coreList.add(new Point(i, j));
                    }
                }
            }

            maxCores = Integer.MIN_VALUE;
            minLength = Integer.MAX_VALUE;
            setLines(0, 0, 0);
            answer.append(String.format("#%d %d%n", tc, minLength));
        }

        System.out.println(answer);
    }

    private static void setLines(int idx, int cntCores, int sumLength) {
        if (idx == coreList.size()) {
            if (maxCores > cntCores) {
                return;
            }

            if (maxCores == cntCores) {
                minLength = Math.min(minLength, sumLength);
                return;
            }

            maxCores = cntCores;
            minLength = sumLength;
            return;
        }

        Point cur = coreList.get(idx);
        for (int[] move : DIRECTIONS) {
            int length = installLine(cur, move);

            if (length > 0) {
                setLines(idx + 1, cntCores + 1, sumLength + length);
                uninstallLine(cur, move, length);
            }
        }

        setLines(idx + 1, cntCores, sumLength);
    }

    private static int installLine(Point cur, int[] move) {
        int nextRow = cur.row;
        int nextCol = cur.col;
        int length = 0;

        while (true) {
            nextRow = nextRow + move[0];
            nextCol = nextCol + move[1];

            if (!isMovable(nextRow, nextCol)) {
                break;
            }

            if (cell[nextRow][nextCol] > 0) {
                uninstallLine(cur, move, length);
                length = 0;
                break;
            }

            cell[nextRow][nextCol]++;
            length++;
        }

        return length;
    }

    private static void uninstallLine(Point cur, int[] move, int length) {
        int nextRow = cur.row;
        int nextCol = cur.col;

        while (length-- > 0) {
            nextRow = nextRow + move[0];
            nextCol = nextCol + move[1];

            if (!isMovable(nextRow, nextCol)) {
                break;
            }

            cell[nextRow][nextCol]--;
        }
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
    }

}
