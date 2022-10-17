package implementation.problem_1051;

import java.util.Scanner;

public class Problem_1051 {

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        map = new int[N][M];

        input.nextLine();
        for (int i = 0; i < N; i++) {
            String line = input.nextLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - 'a';
            }
        }

        for (int size = Math.min(N, M) - 1; size > 0; size--) {
            checkSquareRow(size);
        }

        System.out.println(1);
    }

    private static void checkSquareRow(int size) {
        for (int row = 0; row < N; row++) {
            if (!isInRow(row + size)) {
                continue;
            }

            checkSquareCol(row, size);
        }
    }

    private static void checkSquareCol(int row, int size) {
        for (int col = 0; col < M; col++) {
            if (!isInCol(col + size)) {
                continue;
            }

            int[] square = { map[row][col], map[row + size][col], map[row][col + size], map[row + size][col + size] };
            if (equalAll(square)) {
                System.out.println((size + 1) * (size + 1));
                System.exit(0);
            }
        }
    }

    private static boolean isInCol(int col) {
        return 0 <= col && col < M;
    }

    private static boolean isInRow(int row) {
        return 0 <= row && row < N;
    }

    private static boolean equalAll(int[] square) {
        for (int len = square.length, i = 1; i < len; i++) {
            if (square[i - 1] != square[i]) {
                return false;
            }
        }

        return true;
    }

}
