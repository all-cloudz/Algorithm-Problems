package backtracking.problem_2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problem_2239 {

    private static int[][] board;
    private static List<int[]> zeros;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        zeros = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String line = input.readLine();

            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';

                if (board[i][j] == 0) {
                    zeros.add(new int[] { i, j });
                }
            }
        }

        fillSudoko(0);
    }

    private static void fillSudoko(int idx) {
        if (idx == zeros.size()) {
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer.append(board[i][j]);
                }

                answer.append("\n");
            }
            System.out.print(answer);
            System.exit(0);
        }

        int[] cur = zeros.get(idx);
        boolean[] used = new boolean[10];

        for (int i = 0; i < 9; i++) {
            used[board[i][cur[1]]] = true;
        }

        for (int j = 0; j < 9; j++) {
            used[board[cur[0]][j]] = true;
        }

        for (int endRow = 3 * (cur[0] / 3) + 2, i = endRow - 2; i <= endRow; i++) {
            for (int endCol = 3 * (cur[1] / 3) + 2, j = endCol - 2; j <= endCol; j++) {
                used[board[i][j]] = true;
            }
        }

        for (int i = 1; i < 10; i++) {
            if (!used[i]) {
                board[cur[0]][cur[1]] = i;
                fillSudoko(idx + 1);
                board[cur[0]][cur[1]] = 0;
            }
        }
    }

}
