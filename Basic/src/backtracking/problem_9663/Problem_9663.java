package backtracking.problem_9663;

import java.util.Scanner;

public class Problem_9663 {
    private static int answer;
    private static int N;
    private static int[] board;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        board = new int[N];

        backtracking(0);
        System.out.print(answer);
    }

    private static void backtracking(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[depth] = i;

            if (isPossible(depth)) {
                backtracking(depth + 1);
                /* depth + 1 : 모든 행을 관찰하지 않고 처음으로 조건을 만족시키는 행이 나오면 다음 열로 이동 = DFS
                 * 하나의 경우를 완수하고 나면 이전의 열로 돌아와서 남은 반복문을 수행 = 백트래킹
                 * 최종적으로는 모든 경우를 탐색한 것이지만 브루트 포스와 달리 사전에 불가능한 것을 차단함으로써 성능을 높임 */
            }
        }
    }

    private static boolean isPossible(int depth) {
        if (depth == 0) {
            return true;
        }


        for (int i = 0; i < depth; i++) {
            if (board[i] == board[depth]) {
                return false;
            }

            if (Math.abs(board[i] - board[depth]) == depth - i) {
                return false;
            }
        }

        return true;
    }
}
