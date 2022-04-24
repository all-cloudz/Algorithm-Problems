package backtracking.problem_2580;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem_2580 {
    private static int[][] puzzle = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        backtracking(0, 0);
    }

    private static void backtracking(int depth, int col) throws IOException { // row를 기준으로 depth를 측정
        if (depth == 9) {
            BufferedWriter answer = new BufferedWriter(new OutputStreamWriter(System.out));

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer.write(String.valueOf(puzzle[i][j]) + ' ');
                }
                answer.write('\n');
            }

            answer.flush();
            System.exit(0);
            // exit(0)이 아니라 return을 하게 되면 재귀가 끝나지 않으므로 계속해서 백트래킹을 진행한다.
            // 그 결과 스도쿠를 완성시키는 모든 경우를 출력하게 된다.
        }

        if (col == 9) {
            backtracking(depth + 1, 0);
            return;
        }

        if (puzzle[depth][col] == 0) {
            for (int j = 0; j < 9; j++) {
                if (isPossible(depth, col, j + 1)) {
                    puzzle[depth][col] = j + 1;
                    backtracking(depth, col + 1);
                }
            }

            puzzle[depth][col] = 0; // 스도쿠를 완성하지 못했을 경우에는 백트래킹을 위해 원래 상태로 돌려두어야 한다.
        } else {
            backtracking(depth, col + 1);
        }
    }

    private static boolean isPossible(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == value || puzzle[i][col] == value) {
                return false;
            }
        }

        int squareRow = (row / 3) * 3;
        int squareCol = (col / 3) * 3;

        for (int i = squareRow; i < squareRow + 3; i++) {
            for (int j = squareCol; j < squareCol + 3; j++) {
                if (puzzle[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}