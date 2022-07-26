package implementation.problem_2615;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_2615_Sol2 {
    private static final int BOARD_SIZE = 19;
    private static final int[][] DIRECTION = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 } };

    private static String[][] board = new String[BOARD_SIZE + 2][BOARD_SIZE + 2];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= BOARD_SIZE; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            for (int j = 1; j <= BOARD_SIZE; j++) {
                board[i][j] = tokenizer.nextToken();
            }
        }

        int[] winnerArg = getWinnerArg();

        if (winnerArg == null) {
            System.out.println(0);
            return;
        }

        StringBuilder answer = new StringBuilder();
        answer.append(board[winnerArg[0]][winnerArg[1]]).append('\n')
                .append(winnerArg[0]).append(' ').append(winnerArg[1]);

        System.out.println(answer);
    }

    private static int[] getWinnerArg() {
        for (int row = 1; row <= BOARD_SIZE; row++) {
            for (int col = 1; col <= BOARD_SIZE; col++) {
                if (board[row][col].equals("0")) {
                    continue;
                }

                int[] winnerArg = checkBoard(row, col);
                if (winnerArg != null) {
                    return winnerArg;
                }
            }
        }

        return null;
    }

    private static int[] checkBoard(int row, int col) {
        String cur = board[row][col];

        for (int i = 0; i < DIRECTION.length; i++) {
            int[] move = DIRECTION[i];

            int cnt = 1;
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (cur.equals(board[row - move[0]][col - move[1]])) {
                continue;
            }

            while (cur.equals(board[nextRow][nextCol])) {
                cnt++;
                nextRow += move[0];
                nextCol += move[1];
            }

            if (cnt == 5) {
                return new int[] { row, col };
            }
        }

        return null;
    }
}
