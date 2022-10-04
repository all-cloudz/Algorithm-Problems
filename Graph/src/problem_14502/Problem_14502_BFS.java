package problem_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_14502_BFS {

    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N, M;
    private static int[][] lab;
    private static int[][] copiedLab;

    private static Queue<Point> virus;

    private static int emptySpace;
    private static int maxSafety = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        lab = new int[N][M];
        virus = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(tokenizer.nextToken());

                if (lab[i][j] == 0) {
                    emptySpace++;
                    continue;
                }

                if (lab[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }

        int[] selected = new int[3];
        selectWall(selected, 0, 0);
        System.out.println(maxSafety);
    }

    private static void selectWall(int[] selected, int start, int cnt) {
        if (cnt == 3) {
            copy();
            installWall(selected);
            maxSafety = Math.max(maxSafety, spreadVirus(emptySpace - 3));
            return;
        }

        for (int size = N * M, i = start; i < size; i++) {
            if (lab[i / M][i % M] != 0) {
                continue;
            }

            selected[cnt] = i;
            selectWall(selected, i + 1, cnt + 1);
        }
    }

    private static void copy() {
        copiedLab = new int[N][M];

        for (int i = 0; i < N; i++) {
            System.arraycopy(lab[i], 0, copiedLab[i], 0, M);
        }
    }

    private static void installWall(int[] selected) {
        for (int pos : selected) {
            int row = pos / M;
            int col = pos % M;

            copiedLab[row][col] = 1;
        }
    }

    private static int spreadVirus(int emptySpace) {
        Queue<Point> copiedVirus = new ArrayDeque<>(virus);

        while (!copiedVirus.isEmpty()) {
            Point cur = copiedVirus.poll();

            for (int[] move : DIRECTIONS) {
                int nextRow = cur.row + move[0];
                int nextCol = cur.col + move[1];

                if (!isMovable(nextRow, nextCol)) {
                    continue;
                }

                if (copiedLab[nextRow][nextCol] != 0) {
                    continue;
                }

                copiedLab[nextRow][nextCol] = 2;
                emptySpace--;
                copiedVirus.add(new Point(nextRow, nextCol));
            }
        }

        return emptySpace;
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
    }

}
