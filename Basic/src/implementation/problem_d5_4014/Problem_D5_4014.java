package implementation.problem_d5_4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D5_4014 {

    private static int N;
    private static int X;
    private static int[][] mapForRow;
    private static int[][] mapForCol;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            X = Integer.parseInt(tokenizer.nextToken());
            mapForRow = new int[N][N];
            mapForCol = new int[N][N];

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(input.readLine());

                for (int j = 0; j < N; j++) {
                    mapForRow[i][j] = Integer.parseInt(tokenizer.nextToken());
                    mapForCol[j][i] = mapForRow[i][j];
                }
            }

            int roadCnt = 0;

            for (int i = 0; i < N; i++) {
                if (check(mapForRow, i)) {
                    roadCnt++;
                }

                if (check(mapForCol, i)) {
                    roadCnt++;
                }
            }

            answer.append(String.format("#%d %d%n", tc, roadCnt));
        }

        System.out.println(answer);
    }

    private static boolean check(int[][] map, int row) {
        boolean[] installed = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (map[row][i - 1] == map[row][i]) {
                continue;
            }

            if (map[row][i - 1] - map[row][i] == 1 && forwardCheck(map, installed, row, i)) {
                continue;
            }

            if (map[row][i - 1] - map[row][i] == -1 && backwardCheck(map, installed, row, i - 1)) {
                continue;
            }

            return false;
        }

        return true;
    }

    private static boolean forwardCheck(int[][] map, boolean[] installed, int row, int col) {
        for (int i = col + 1; i < col + X; i++) {
            if (!isMovable(row, i) || installed[i] || map[row][i] != map[row][col]) {
                return false;
            }
        }

        for (int i = col; i < col + X; i++) {
            installed[i] = true;
        }

        return true;
    }

    private static boolean backwardCheck(int[][] map, boolean[] installed, int row, int col) {
        for (int i = col - X + 1; i < col; i++) {
            if (!isMovable(row, i) || installed[i] || map[row][i] != map[row][col]) {
                return false;
            }
        }

        for (int i = col - X + 1; i < col; i++) {
            installed[i] = true;
        }

        return true;
    }

    private static boolean isMovable(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < N;
    }

}
