package implementation.code_빙하;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Code_빙하_Sol1 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static int M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        int[] meltInfo = melt();
        System.out.println(meltInfo[0] + " " + meltInfo[1]);
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
    }

    private static int[] melt() {
        Queue<int[]> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[N][M];
        boolean[][] melting = new boolean[N][M];

        points.add(new int[] { 0, 0 });
        int level = 0;
        int prevMeltedCount = 0;

        while (!points.isEmpty()) {
            init(discovered, melting);
            addBegins(points.peek(), points, discovered);

            for (int size = points.size(), i = 0; i < size; i++) {
                int[] cur = points.poll();

                for (int[] dir : DIRECTIONS) {
                    int nextRow = cur[0] + dir[0];
                    int nextCol = cur[1] + dir[1];

                    if (!inRange(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                        continue;
                    }

                    if (map[nextRow][nextCol] == 1) {
                        melting[nextRow][nextCol] = true;
                    }

                    points.add(new int[] { nextRow, nextCol });
                    discovered[nextRow][nextCol] = true;
                }
            }

            int curMeltedCount = getCurMeltedCount(melting);
            if (curMeltedCount == 0) {
                return new int[] { level, prevMeltedCount };
            }

            level++;
            prevMeltedCount = curMeltedCount;
        }

        return null;
    }

    private static void init(boolean[][] discovered, boolean[][] melting) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                discovered[i][j] = false;
                melting[i][j] = false;
            }
        }
    }

    private static void addBegins(int[] cur, Queue<int[]> points, boolean[][] visited) {
        if (visited[cur[0]][cur[1]]) {
            return;
        }

        visited[cur[0]][cur[1]] = true;

        for (int[] dir : DIRECTIONS) {
            int nextRow = cur[0] + dir[0];
            int nextCol = cur[1] + dir[1];

            if (inRange(nextRow, nextCol) && map[nextRow][nextCol] == 0) {
                int[] next = { nextRow, nextCol };
                points.add(next);
                addBegins(next, points, visited);
            }
        }
    }

    private static int getCurMeltedCount(boolean[][] melting) {
        int curMeltedCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (melting[i][j]) {
                    map[i][j] = 0;
                    curMeltedCount++;
                }
            }
        }
        return curMeltedCount;
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
    }

}
