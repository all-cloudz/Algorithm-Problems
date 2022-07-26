package implementation.problem_2615;

import java.io.*;

public class Problem_2615_Sol1 {
    private static final int BOARD_SIZE = 19;
    private static final int[][] DIRECTION = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 } };

    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 19; i++) {
            board[i] = input.readLine().replace(" ", "").toCharArray();
        }

        int[] winnerArg = getWinnerArg();

        if (winnerArg == null) {
            System.out.println(0);
            return;
        }

        StringBuilder answer = new StringBuilder();
        answer.append(board[winnerArg[0]][winnerArg[1]]).append('\n')
                .append(winnerArg[0] + 1).append(' ').append(winnerArg[1] + 1);

        System.out.println(answer);
    }

    private static int[] getWinnerArg() {
        for (int i = 0; i < DIRECTION.length; i++) {
            int[] move = DIRECTION[i];
            boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];

            int[] winnerArg = checkBoard(visited, move);
            if (winnerArg != null) {
                return winnerArg;
            }
        }

        return null;
    }

    private static int[] checkBoard(boolean[][] visited, int[] move) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == '0' || visited[row][col]) {
                    continue;
                }

                visited[row][col] = true;

                if (isWinner(row, col, visited, move)) {
                    return (move != DIRECTION[3]) ? new int[] { row, col } : new int[] { row + 4, col - 4 };
                }
            }
        }

        return null;
    }

    private static boolean isWinner(int row, int col, boolean[][] visited, int[] move) {
        char cur = board[row][col];
        int cnt = 1;

        while (true) {
            row += move[0];
            col += move[1];

            if (row >= 19 || col >= 19 || row < 0 || col < 0) {
                break;
            }

            if (cur != board[row][col]) {
                break;
            }

            visited[row][col] = true;
            cnt++;
        }

        if (cnt != 5) {
            return false;
        }

        return true;
    }
}
