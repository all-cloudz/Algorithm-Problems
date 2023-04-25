package implementation.code_십자_모양_폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Code_십자_모양_폭발 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int n;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(input.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int row = Integer.parseInt(tokenizer.nextToken()) - 1;
        int col = Integer.parseInt(tokenizer.nextToken()) - 1;

        bomb(row, col);
        drop();

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer.append(board[i][j]).append(' ');
            }

            answer.append('\n');
        }

        System.out.println(answer);
    }

    private static void drop() {
        int[][] result = new int[n][n];

        for (int col = 0; col < n; col++) {
            int idx = n - 1;

            for (int row = n - 1; row >= 0; row--) {
                if (board[row][col] == 0) {
                    continue;
                }

                result[idx--][col] = board[row][col];
            }
        }

        board = result;
    }

    private static void bomb(int row, int col) {
        int range = board[row][col];
        for (int[] dir : DIRECTIONS) {
            for (int i = 0; i < range; i++) {
                int nextRow = row + dir[0] * i;
                int nextCol = col + dir[1] * i;

                if (!inRange(nextRow, nextCol)) {
                    continue;
                }

                board[nextRow][nextCol] = 0;
            }
        }
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

}
