package problem_lv1_64061;

import java.util.Stack;

public class Problem_Lv1_64061 {
    private static int answer;
    private static Stack<Integer> bucket = new Stack<>();

    public static int solution(int[][] board, int[] moves) {
        for (int column : moves) {
            int dull = picker(board, --column);
            moveDull(dull);
        }

        return answer;
    }

    private static int picker(int[][] board, int column) {
        for (int i = 0; i < board.length; i++) {
            int pick = board[i][column];

            if (pick != 0) {
                board[i][column] = 0;
                return pick;
            }
        }

        return 0;
    }

    private static void moveDull(int dull) {
        if (dull == 0) {
            return;
        }

        if (!bucket.empty() && bucket.peek() == dull) {
            bucket.pop();
            answer += 2;
            return;
        }

        bucket.push(dull);
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };

        int[] moves = new int[] {1, 5, 3, 5, 1, 2, 1, 4};

        System.out.print(solution(board, moves));
    }
}