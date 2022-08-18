package backtracking.problem_1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_1987_Opti {
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int R, C;
    private static char[][] board;
    private static int[][] tracker;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        tracker = new int[R][C];

        for (int i = 0; i < R; i++) {
            board[i] = input.readLine().toCharArray();
        }

        moveItem(0, 0, 0, 1);
        System.out.println(answer);
    }

    private static void moveItem(int row, int col, int visited, int cnt) {
        // 만난 문자 기록
        visited = visited | (1 << board[row][col] - 'A');

        // 방문했던 문자의 기록이 같으면 이미 최댓값에 반영되어 있으므로 더 이상 탐색하지 않는다.
        if (tracker[row][col] == visited) {
            return;
        }

        // 현재까지 만났던 문자를 기록하고 최댓값 계산
        tracker[row][col] = visited;
        answer = Math.max(answer, cnt);

        // 다음으로 이동
        for (int[] move : DIRECTIONS) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isMovable(nextRow, nextCol, visited)) {
                moveItem(nextRow, nextCol, visited, cnt + 1);
            }
        }
    }

    private static boolean isMovable(int row, int col, int visited) {
        // 보드를 나가면 안 돼
        if (row < 0 || row >= R || col < 0 || col >= C) {
            return false;
        }

        // 같은 알파벳 안 돼
        return (visited & (1 << board[row][col] - 'A')) == 0;
    }
}
