package implementation.code_빙하;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Code_빙하_Sol2 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final int WATER = 0;

    private static int N;
    private static int M;
    private static int[][] map;
    private static int time;
    private static int lastMeltedCount;

    public static void main(String[] args) throws IOException {
        init();
        melt();
        System.out.println(time + " " + lastMeltedCount);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(input.readLine().split(" "))
                           .mapToInt(Integer::parseInt)
                           .toArray();
        }

        time = 0;
        lastMeltedCount = 0;
    }

    private static void melt() {
        Queue<int[]> waters = new ArrayDeque<>();
        boolean[][] discovered = new boolean[N][M];

        waters.add(new int[] { 0, 0 });
        discovered[0][0] = true;

        while (!waters.isEmpty()) {
            Queue<int[]> melted = getMeltedGlacier(waters, discovered);

            if (!melted.isEmpty()) {
                time++;
                lastMeltedCount = waters.size();
            }

            waters = melted;
        }
    }

    private static Queue<int[]> getMeltedGlacier(Queue<int[]> waters, boolean[][] discovered) {
        Queue<int[]> melting = new ArrayDeque<>();

        while (!waters.isEmpty()) {
            int[] cur = waters.poll();

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur[0] + dir[0];
                int nextCol = cur[1] + dir[1];

                if (!inRange(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                int[] next = { nextRow, nextCol };
                if (map[nextRow][nextCol] == WATER) {
                    waters.offer(next);
                } else {
                    melting.offer(next);
                }

                discovered[nextRow][nextCol] = true;
            }
        }

        return melting;
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
    }

}
