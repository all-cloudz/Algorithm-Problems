package problem_d5_5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_D5_5656_BFS {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int[] selected;

    private static int H, W;
    private static int[][] board;
    private static int minRock;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            W = Integer.parseInt(tokenizer.nextToken());
            H = Integer.parseInt(tokenizer.nextToken());
            board = new int[H][W];

            for (int i = 0; i < H; i++) {
                tokenizer = new StringTokenizer(input.readLine());

                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            minRock = Integer.MAX_VALUE;
            selected = new int[N];
            selectFallPos(board, 0);

            answer.append(String.format("#%d %d%n", tc, minRock));
        }

        System.out.println(answer);
    }

    private static void selectFallPos(int[][] board, int cnt) {
        if (cnt == N) {
            minRock = Math.min(minRock, countRock(board));
            return;
        }

        int[][] copied = new int[H][W];
        for (int col = 0; col < W; col++) {
            int row = 0;

            while (row < H && board[row][col] == 0) {
                row++;
            }

            if (row == H) {
                selectFallPos(board, cnt + 1);
                continue;
            }

            copy(board, copied);
            boom(copied, row, col);
            downRock(copied);
            selectFallPos(copied, cnt + 1);
        }
    }

    private static void copy(int[][] src, int[][] desc) {
        for (int i = 0; i < H; i++) {
            System.arraycopy(src[i], 0, desc[i], 0, W);
        }
    }

    private static void boom(int[][] copied, int row, int col) {
        Queue<int[]> rocks = new ArrayDeque<>();

        if (copied[row][col] > 1) {
            rocks.offer(new int[] { row, col, copied[row][col] });
        }

        copied[row][col] = 0;

        while (!rocks.isEmpty()) {
            int[] cur = rocks.poll();

            for (int[] move : DIRECTIONS) {
                int nextRow = cur[0];
                int nextCol = cur[1];

                for (int i = 1; i < cur[2]; i++) {
                    nextRow += move[0];
                    nextCol += move[1];

                    if (!isMovable(nextRow, nextCol) || copied[nextRow][nextCol] == 0) {
                        continue;
                    }

                    if (copied[nextRow][nextCol] > 1) {
                        rocks.offer(new int[] { nextRow, nextCol, copied[nextRow][nextCol] });
                    }

                    copied[nextRow][nextCol] = 0;
                }
            }
        }
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < H && 0 <= nextCol && nextCol < W;
    }

    private static void downRock(int[][] copied) {
        Stack<Integer> rocks = new Stack<>();

        for (int j = 0; j < W; j++) {
            for (int i = 0; i < H; i++) {
                if (copied[i][j] == 0) {
                    continue;
                }

                rocks.push(copied[i][j]);
                copied[i][j] = 0;
            }

            for (int size = H - rocks.size(), i = H - 1; i >= size; i--) {
                copied[i][j] = rocks.pop();
            }
        }
    }

    private static int countRock(int[][] board) {
        int cnt = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] != 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

}
