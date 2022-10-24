package implementation.problem_2931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Problem_2931_Sol1 {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final Map<Character, int[]> moveIdxOfBlock;

    private static int R, C;
    private static char[][] map;
    private static Point cur;
    private static int intoIdx;

    static {
        moveIdxOfBlock = new HashMap<>();
        moveIdxOfBlock.put('|', new int[] { 0, 1, 0, 0 });
        moveIdxOfBlock.put('-', new int[] { 0, 0, 2, 3 });
        moveIdxOfBlock.put('+', new int[] { 0, 1, 2, 3 });
        moveIdxOfBlock.put('1', new int[] { 3, 0, 1, 0 });
        moveIdxOfBlock.put('2', new int[] { 0, 3, 0, 0 });
        moveIdxOfBlock.put('3', new int[] { 0, 2, 0, 0 });
        moveIdxOfBlock.put('4', new int[] { 2, 0, 0, 1 });
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = input.readLine();

            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'M') {
                    cur = new Point(i, j);
                    map[i][j] = '.';
                }

                if (map[i][j] == 'Z') {
                    map[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = cur.row + DIRECTIONS[i][0];
            int nextCol = cur.col + DIRECTIONS[i][1];

            if (isIn(nextRow, nextCol) && map[nextRow][nextCol] != '.') {
                cur = new Point(nextRow, nextCol);
                intoIdx = i;
                break;
            }
        }

        while (map[cur.row][cur.col] != '.') {
            intoIdx = moveIdxOfBlock.get(map[cur.row][cur.col])[intoIdx];
            cur = new Point(cur.row + DIRECTIONS[intoIdx][0], cur.col + DIRECTIONS[intoIdx][1]);
        }

        System.out.printf("%d %d %s", cur.row + 1, cur.col + 1, installBlock());
    }

    private static char installBlock() {
        boolean[] outType = new boolean[4];

        for (int i = 0; i < 4; i++) {
            int nextRow = cur.row + DIRECTIONS[i][0];
            int nextCol = cur.col + DIRECTIONS[i][1];

            if (!isIn(nextRow, nextCol) || map[nextRow][nextCol] == '.') {
                continue;
            }

            char block = map[nextRow][nextCol];

            if (i == 0 && (block == '|' || block == '+' || block == '1' || block == '4')) {
                outType[i] = true;
                continue;
            }

            if (i == 1 && (block == '|' || block == '+' || block == '2' || block == '3')) {
                outType[i] = true;
                continue;
            }

            if (i == 2 && (block == '-' || block == '+' || block == '1' || block == '2')) {
                outType[i] = true;
                continue;
            }

            if (i == 3 && (block == '-' || block == '+' || block == '3' || block == '4')) {
                outType[i] = true;
            }
        }

        if (outType[0] && outType[1] && outType[2] && outType[3]) {
            return map[cur.row][cur.col] = '+';
        }

        if (outType[0] && outType[1]) {
            return map[cur.row][cur.col] = '|';
        }

        if (outType[0] && outType[2]) {
            return map[cur.row][cur.col] = '3';
        }

        if (outType[0] && outType[3]) {
            return map[cur.row][cur.col] = '2';
        }

        if (outType[1] && outType[2]) {
            return map[cur.row][cur.col] = '4';
        }

        if (outType[1] && outType[3]) {
            return map[cur.row][cur.col] = '1';
        }

        if (outType[2] && outType[3]) {
            return map[cur.row][cur.col] = '-';
        }

        return '\0';
    }

    private static boolean isIn(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C;
    }

}
