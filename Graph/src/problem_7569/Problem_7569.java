package problem_7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_7569 {

    private static class Point {
        private int z;
        private int y;
        private int x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 } };

    private static int N, M, H;
    private static int[][][] tomatoBoxes;
    private static Queue<Point> tomatoes;
    private static boolean[][][] discovered;
    private static int unriped;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        H = Integer.parseInt(tokenizer.nextToken());

        tomatoBoxes = new int[H][N][M];
        tomatoes = new ArrayDeque<>();
        discovered = new boolean[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(input.readLine());

                for (int j = 0; j < M; j++) {
                    tomatoBoxes[k][i][j] = Integer.parseInt(tokenizer.nextToken());

                    if (tomatoBoxes[k][i][j] == 1) {
                        tomatoes.offer(new Point(k, i, j));
                        discovered[k][i][j] = true;
                        continue;
                    }

                    if (tomatoBoxes[k][i][j] == 0) {
                        unriped++;
                    }
                }
            }
        }

        if (unriped == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(ripeTime());
    }

    private static int ripeTime() {
        int time = -1;

        while (!tomatoes.isEmpty()) {
            for (int levelSize = tomatoes.size(), i = 0; i < levelSize; i++) {
                Point cur = tomatoes.poll();

                for (int[] move : DIRECTIONS) {
                    int nextZ = cur.z + move[0];
                    int nextY = cur.y + move[1];
                    int nextX = cur.x + move[2];

                    if (!isMovable(nextZ, nextY, nextX)) {
                        continue;
                    }

                    if (!discovered[nextZ][nextY][nextX]) {
                        tomatoes.offer(new Point(nextZ, nextY, nextX));
                        tomatoBoxes[cur.z][cur.y][cur.x] = 1;
                        discovered[nextZ][nextY][nextX] = true;
                        unriped--;
                    }
                }
            }

            time++;
        }

        if (unriped != 0) {
            time = -1;
        }

        return time;
    }

    private static boolean isMovable(int nextZ, int nextY, int nextX) {
        if (nextZ < 0 || nextZ >= H || nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
            return false;
        }

        return tomatoBoxes[nextZ][nextY][nextX] != -1;
    }

}
