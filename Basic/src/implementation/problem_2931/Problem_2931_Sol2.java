package implementation.problem_2931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_2931_Sol2 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static int R, C;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = input.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.' && canInstallBlock(i, j)) {
                    System.out.printf("%d %d %s", i + 1, j + 1, map[i][j]);
                    System.exit(0);
                }
            }
        }
    }

    private static boolean canInstallBlock(int row, int col) {
        boolean[] outType = new boolean[4];

        for (int i = 0; i < 4; i++) {
            int nextRow = row + DIRECTIONS[i][0];
            int nextCol = col + DIRECTIONS[i][1];

            if (!isIn(nextRow, nextCol) || map[nextRow][nextCol] == '.') {
                continue;
            }

            if (map[nextRow][nextCol] == 'M' || map[nextRow][nextCol] == 'Z') {
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
            map[row][col] = '+';
            return true;
        }

        if (outType[0] && outType[1]) {
            map[row][col] = '|';
            return true;
        }

        if (outType[0] && outType[2]) {
            map[row][col] = '3';
            return true;
        }

        if (outType[0] && outType[3]) {
            map[row][col] = '2';
            return true;
        }

        if (outType[1] && outType[2]) {
            map[row][col] = '4';
            return true;
        }

        if (outType[1] && outType[3]) {
            map[row][col] = '1';
            return true;
        }

        if (outType[2] && outType[3]) {
            map[row][col] = '-';
            return true;
        }

        return false;
    }

    private static boolean isIn(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C;
    }

}
