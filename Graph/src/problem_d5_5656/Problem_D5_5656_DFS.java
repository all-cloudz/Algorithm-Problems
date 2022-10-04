package problem_d5_5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_D5_5656_DFS {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int[] selected;

    private static int H, W;
    private static int[][] board;
    private static int[][] copied;
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
            selectFallPos(0);

            answer.append(String.format("#%d %d%n", tc, minRock));
        }

        System.out.println(answer);
    }

    private static void selectFallPos(int cnt) {
        if (cnt == N) {
            copy();

            for (int pos : selected) {
                fallBall(pos);
            }

            minRock = Math.min(minRock, countRock());
            return;
        }

        for (int i = 0; i < W; i++) {
            selected[cnt] = i;
            selectFallPos(cnt + 1);
        }
    }

    private static void copy() {
        copied = new int[H][W];

        for (int i = 0; i < H; i++) {
            System.arraycopy(board[i], 0, copied[i], 0, W);
        }

    }

    private static void fallBall(int pos) {
        int row = 0;

        while (row < H && copied[row][pos] == 0) {
            row++;
        }

        if (row == H) {
            return;
        }

        boom(row, pos, copied[row][pos]);
        downRock();
    }

    private static void boom(int row, int col, int cnt) {
        copied[row][col] = 0;

        if (cnt == 1) {
            return;
        }

        for (int[] move : DIRECTIONS) {
            int nextRow = row;
            int nextCol = col;

            for (int i = 1; i < cnt; i++) {
                nextRow += move[0];
                nextCol += move[1];

                if (!isMovable(nextRow, nextCol)) {
                    continue;
                }

                if (copied[nextRow][nextCol] > 0) {
                    boom(nextRow, nextCol, copied[nextRow][nextCol]);
                }
            }
        }
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < H && 0 <= nextCol && nextCol < W;
    }

    private static void downRock() {
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

    private static int countRock() {
        int cnt = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (copied[i][j] != 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

}
