package problem_d4_1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_D4_1953 {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][][] TYPE = {
            {},
            { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } },
            { { -1, 0 }, { 1, 0 } },
            { { 0, -1 }, { 0, 1 } },
            { { -1, 0 }, { 0, 1 } },
            { { 1, 0 }, { 0, 1 } },
            { { 1, 0 }, { 0, -1 } },
            { { -1, 0 }, { 0, -1 } }
    };

    private static int N, M;
    private static int[][] map;
    private static Point hidden;
    private static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            hidden = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            L = Integer.parseInt(tokenizer.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
//                map[i] = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                tokenizer = new StringTokenizer(input.readLine());

                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            answer.append(String.format("#%d %d%n", tc, hide()));
        }

        System.out.println(answer);
    }

    private static int hide() {
        int cnt = 0;

        Queue<Point> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[N][M];

        points.offer(hidden);
        discovered[hidden.row][hidden.col] = true;

        while (!points.isEmpty()) {
            if (L <= 0) {
                break;
            }

            for (int levelSize = points.size(), i = 0; i < levelSize; i++) {
                Point cur = points.poll();
                cnt++;

                for (int[] move : TYPE[map[cur.row][cur.col]]) {
                    int nextRow = cur.row + move[0];
                    int nextCol = cur.col + move[1];

                    if (!isMovable(cur.row, cur.col, nextRow, nextCol)) {
                        continue;
                    }

                    if (!discovered[nextRow][nextCol]) {
                        points.offer(new Point(nextRow, nextCol));
                        discovered[nextRow][nextCol] = true;
                    }
                }
            }

            L--;
        }

        return cnt;
    }

    private static boolean isMovable(int row, int col, int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
            return false;
        }

        if (map[nextRow][nextCol] == 0) {
            return false;
        }

        if (row == nextRow) {
            if (col < nextCol) {
                return movableRight(nextRow, nextCol);
            }

            return movableLeft(nextRow, nextCol);
        }

        if (row < nextRow) {
            return movableDown(nextRow, nextCol);
        }

        return movableUp(nextRow, nextCol);
    }

    private static boolean movableRight(int nextRow, int nextCol) {
        return map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 3 || map[nextRow][nextCol] == 6 || map[nextRow][nextCol] == 7;
    }

    private static boolean movableLeft(int nextRow, int nextCol) {
        return map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 3 || map[nextRow][nextCol] == 4 || map[nextRow][nextCol] == 5;
    }

    private static boolean movableDown(int nextRow, int nextCol) {
        return map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 2 || map[nextRow][nextCol] == 4 || map[nextRow][nextCol] == 7;
    }

    private static boolean movableUp(int nextRow, int nextCol) {
        return map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 2 || map[nextRow][nextCol] == 5 || map[nextRow][nextCol] == 6;
    }

}
