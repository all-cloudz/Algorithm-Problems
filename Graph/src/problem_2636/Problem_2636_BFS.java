package problem_2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_2636_BFS {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int R, C;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int time = 0;
        int cheeseAfterHour = 0;

        while (isCheese()) {
            cheeseAfterHour = countCheese();
            bfs();
            time++;
        }

        System.out.println(time);
        System.out.println(cheeseAfterHour);
    }

    private static boolean isCheese() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int countCheese() {
        int cnt = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void bfs() {
        Queue<Point> cheeses = new ArrayDeque<>();
        boolean[][] discovered = new boolean[R][C];

        cheeses.offer(new Point(0, 0));
        discovered[0][0] = true;

        while (!cheeses.isEmpty()) {
            Point cur = cheeses.poll();

            for (int[] move : DIRECTIONS) {
                int nextRow = cur.row + move[0];
                int nextCol = cur.col + move[1];

                if (!isMovable(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                if (map[nextRow][nextCol] == 0) {
                    cheeses.offer(new Point(nextRow, nextCol));
                    discovered[nextRow][nextCol] = true;
                    continue;
                }

                if (map[nextRow][nextCol] == 1) {
                    map[nextRow][nextCol] = 2;
                    discovered[nextRow][nextCol] = true;
                }
            }
        }

        melt();
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C;
    }

    private static void melt() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }
    }

}
