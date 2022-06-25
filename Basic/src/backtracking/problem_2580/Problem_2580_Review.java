package backtracking.problem_2580;

import java.io.*;
import java.util.*;

public class Problem_2580_Review {
    private static StringBuilder answer = new StringBuilder();
    private static int[][] puzzle = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        solve(0, 0);
    }

    private static void solve(int row, int col) {
        if (col == 9) {
            solve(row + 1, 0);
            return;
        }

        if (row == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer.append(puzzle[i][j]).append(' ');
                }

                answer.append('\n');
            }

            System.out.print(answer);
            System.exit(0);
        }

        if (puzzle[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isPossible(row, col, i)) {
                    puzzle[row][col] = i;
                    solve(row, col + 1);
                }
            }

            puzzle[row][col] = 0;
            return;
        }

        solve(row, col + 1);
    }

    private static boolean isPossible(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == value || puzzle[i][col] == value) {
                return false;
            }
        }

        int rowSquare = (row / 3) * 3;
        int colSquare = (col / 3) * 3;

        for (int i = rowSquare; i < rowSquare + 3; i++) {
            for (int j = colSquare; j < colSquare + 3; j++) {
                if (puzzle[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
