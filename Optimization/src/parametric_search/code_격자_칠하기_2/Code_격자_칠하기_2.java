package parametric_search.code_격자_칠하기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Code_격자_칠하기_2 {

    private static int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(input.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        System.out.println(getMinDiff());
    }

    private static int getMinDiff() {
        int left = 0;
        int right = 1_000_001;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (isHalfArea(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private static boolean isHalfArea(int diff) {
        boolean[][] discovered = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (discovered[i][j]) {
                    continue;
                }

                if ((searchDepthFirst(discovered, i, j, diff) << 1) >= N * N) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int searchDepthFirst(boolean[][] discovered, int i, int j, int diff) {
        Stack<int[]> points = new Stack<>();

        points.push(new int[] { i, j });
        discovered[i][j] = true;
        int count = 1;

        while (!points.isEmpty()) {
            int[] cur = points.pop();

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur[0] + dir[0];
                int nextCol = cur[1] + dir[1];

                if (!canMove(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                if (Math.abs(board[cur[0]][cur[1]]- board[nextRow][nextCol]) <= diff) {
                    points.push(new int[] {nextRow, nextCol});
                    discovered[nextRow][nextCol] = true;
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean canMove(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
    }

}
