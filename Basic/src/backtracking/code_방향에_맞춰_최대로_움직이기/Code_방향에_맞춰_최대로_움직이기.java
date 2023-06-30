package backtracking.code_방향에_맞춰_최대로_움직이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Code_방향에_맞춰_최대로_움직이기 {

    private static final int[][] DIRECTIONS = { { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

    private static int n;
    private static int[][] map;
    private static int[][] movings;
    private static int row;
    private static int col;
    private static int maxCnt;

    public static void main(String[] args) throws IOException {
        init();
        traverse(row, col, 0);
        System.out.println(maxCnt);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(input.readLine().split(" "))
                           .mapToInt(Integer::parseInt)
                           .toArray();
        }

        movings = new int[n][n];
        for (int i = 0; i < n; i++) {
            movings[i] = Arrays.stream(input.readLine().split(" "))
                               .mapToInt(Integer::parseInt)
                               .toArray();
        }

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        row = Integer.parseInt(tokenizer.nextToken()) - 1;
        col = Integer.parseInt(tokenizer.nextToken()) - 1;
        maxCnt = Integer.MIN_VALUE;
    }

    private static void traverse(int row, int col, int cnt) {
        int[] dir = DIRECTIONS[movings[row][col]];
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        while (inRange(nextRow, nextCol)) {
            if (map[row][col] < map[nextRow][nextCol]) {
                traverse(nextRow, nextCol, cnt + 1);
            }

            nextRow = nextRow + dir[0];
            nextCol = nextCol + dir[1];
        }

        maxCnt = Math.max(maxCnt, cnt);
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

}
