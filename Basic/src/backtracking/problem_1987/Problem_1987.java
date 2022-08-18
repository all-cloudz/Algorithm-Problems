package backtracking.problem_1987;

import java.io.*;
import java.util.*;

public class Problem_1987 {
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int R, C;
    private static char[][] board;
    private static boolean[] visited;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        visited = new boolean['Z' + 1];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < R; i++) {
            board[i] = input.readLine().toCharArray();
        }

        moveItem(0, 0, 1);
        System.out.println(max);
    }

    private static void moveItem(int row, int col, int cnt) {
        visited[board[row][col]] = true;
        int nextRow, nextCol;

        // 네 방향 탐색
        for (int[] move : DIRECTIONS) {
            nextRow = row + move[0];
            nextCol = col + move[1];

            if (isMovable(nextRow, nextCol)) {
                moveItem(nextRow, nextCol, cnt + 1);
            }
        }

        // 갈 곳이 없으면 최댓값 기록
        max = Math.max(max, cnt);
        visited[board[row][col]] = false;
    }

    private static boolean isMovable(int row, int col) {
        // 보드를 나가면 안 돼
        if (row < 0 || row >= R || col < 0 || col >= C) {
            return false;
        }

        // 같은 알파벳 안 돼
        if (visited[board[row][col]]) {
            return false;
        }

        return true;
    }
}
