package implementation.boj_1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Boj_1113 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int M;
    private static int[][] land;

    public static void main(final String[] args) throws IOException {
        init();
        System.out.println(poolArea());
    }

    private static void init() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        land = new int[N][M];
        for (int i = 0; i < N; i++) {
            land[i] = input.readLine()
                           .chars()
                           .map(charHeight -> charHeight - '0')
                           .toArray();
        }
    }

    private static int poolArea() {
        final int maxHeight = Arrays.stream(land)
                                    .flatMapToInt(IntStream::of)
                                    .max()
                                    .orElseThrow();

        return IntStream.rangeClosed(2, maxHeight)
                        .map(Boj_1113::poolAreaUnderHeight)
                        .sum();
    }

    private static int poolAreaUnderHeight(final int height) {
        int count = 0;
        final boolean[][] discovered = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (!discovered[row][col] && land[row][col] < height) {
                    final PoolResult poolResult = bfs(row, col, height);
                    count += poolResult.count;
                    update(discovered, poolResult.discovered);
                }
            }
        }

        return count;
    }

    private static void update(final boolean[][] src, final boolean[][] target) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                src[i][j] = (src[i][j] || target[i][j]);
            }
        }
    }

    private static PoolResult bfs(final int row, final int col, final int height) {
        final Queue<Point> points = new ArrayDeque<>();
        final boolean[][] discovered = new boolean[N][M];

        points.offer(new Point(row, col));
        discovered[row][col] = true;

        boolean bounded = true;
        int count = 1;
        while (!points.isEmpty()) {
            final Point cur = points.poll();

            for (final int[] dir : DIRECTIONS) {
                final int nextRow = cur.row + dir[0];
                final int nextCol = cur.col + dir[1];

                if (!canMove(nextRow, nextCol)) {
                    bounded = false;
                    continue;
                }

                if (!discovered[nextRow][nextCol] && land[nextRow][nextCol] < height) {
                    points.offer(new Point(nextRow, nextCol));
                    discovered[nextRow][nextCol] = true;
                    count++;
                }
            }
        }

        if (!bounded) {
            count = 0;
        }

        return new PoolResult(count, discovered);
    }

    private static boolean canMove(final int nextRow, final int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
    }

    private static class Point {
        private final int row;
        private final int col;

        public Point(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class PoolResult {
        private final int count;
        private final boolean[][] discovered;

        public PoolResult(final int count, final boolean[][] discovered) {
            this.count = count;
            this.discovered = discovered;
        }
    }
}
