package implementation.code_돌_잘_치우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Code_돌_잘_치우기 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int n;
    private static int k;
    private static int m;
    private static int[][] board;
    private static List<int[]> rocks;
    private static List<int[]> startings;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        moveBoard(0, 0);
        System.out.println(max);
    }

    private static void moveBoard(int count, int visited) {
        if (count == m) {
            removeRocks(visited);
            max = Math.max(max, moveStartings());
            resetRocks(visited);
            return;
        }

        for (int size = rocks.size(), i = 0; i < size; i++) {
            if ((visited >> i & 1) == 1) {
                continue;
            }

            moveBoard(count + 1, visited | 1 << i);
        }
    }

    private static int moveStartings() {
        Queue<int[]> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[n][n];

        points.addAll(startings);
        for (int[] starting : startings) {
            discovered[starting[0]][starting[1]] = true;
        }

        while (!points.isEmpty()) {
            int[] cur = points.poll();

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur[0] + dir[0];
                int nextCol = cur[1] + dir[1];

                if (!inRange(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                if (board[nextRow][nextCol] == 1) {
                    continue;
                }

                points.add(new int[] { nextRow, nextCol });
                discovered[nextRow][nextCol] = true;
            }
        }

        return countMoved(discovered);
    }

    private static int countMoved(boolean[][] discovered) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (discovered[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

    private static void removeRocks(int visited) {
        for (int size = rocks.size(), i = 0; i < size; i++) {
            if ((visited >> i & 1) == 0) {
                continue;
            }

            int[] rock = rocks.get(i);
            board[rock[0]][rock[1]] = 0;
        }
    }

    private static void resetRocks(int visited) {
        for (int size = rocks.size(), i = 0; i < size; i++) {
            if ((visited >> i & 0) == 1) {
                continue;
            }

            int[] rock = rocks.get(i);
            board[rock[0]][rock[1]] = 1;
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        board = new int[n][n];
        rocks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());

                if (board[i][j] == 1) {
                    rocks.add(new int[] { i, j });
                }
            }
        }

        startings = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            startings.add(new int[] { row, col });
        }
    }

}
