package implementation.problem_나무박멸;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_나무박멸 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    private static final int[][] DIAGONALS = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

    private static int n;
    private static int[][] map;
    private static int m;
    private static int k;
    private static int c;
    private static int[][] sprayed;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][n];
        sprayed = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(input.readLine().split(" "))
                           .mapToInt(Integer::parseInt)
                           .toArray();
        }

        int destroyCnt = 0;
        while (m-- > 0) {
            afterYear();
            destroyCnt += simulate();
        }

        System.out.println(destroyCnt);
    }

    private static void afterYear() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sprayed[i][j] > 0) {
                    sprayed[i][j]--;
                }
            }
        }
    }

    private static int simulate() {
        growTrees();
        breedTrees();
        return spray();
    }

    private static void growTrees() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isTree(i, j)) {
                    map[i][j] += countNearTrees(i, j);
                }
            }
        }
    }

    private static void breedTrees() {
        int[][] breeds = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isTree(i, j)) {
                    breedTree(breeds, i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += breeds[i][j];
            }
        }
    }

    private static void breedTree(int[][] breeds, int row, int col) {
        List<int[]> empties = getEmpties(row, col);
        if (empties.isEmpty()) {
            return;
        }

        int breedingCnt = map[row][col] / empties.size();
        empties.forEach(point -> breeds[point[0]][point[1]] += breedingCnt);
    }

    private static int spray() {
        int maxDestroyedCnt = -1;
        int[] arg = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isTree(i, j)) {
                    continue;
                }

                int destroyedCnt = countDestroyedTrees(i, j);
                if (maxDestroyedCnt < destroyedCnt) {
                    maxDestroyedCnt = destroyedCnt;
                    arg = new int[] { i, j };
                }
            }
        }

        if (arg == null) {
            return 0;
        }

        map[arg[0]][arg[1]] = 0;
        sprayed[arg[0]][arg[1]] = c + 1;
        for (int[] dir : DIAGONALS) {
            for (int i = 1; i <= k; i++) {
                int nextRow = arg[0] + dir[0] * i;
                int nextCol = arg[1] + dir[1] * i;

                if (!inRange(nextRow, nextCol)) {
                    break;
                }

                if (map[nextRow][nextCol] == 0 || map[nextRow][nextCol] == -1) {
                    sprayed[nextRow][nextCol] = c + 1;
                    break;
                }

                map[nextRow][nextCol] = 0;
                sprayed[nextRow][nextCol] = c + 1;
            }
        }

        return maxDestroyedCnt;
    }

    private static boolean isTree(int row, int col) {
        return map[row][col] > 0;
    }

    private static int countNearTrees(int row, int col) {
        int count = 0;

        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (!inRange(nextRow, nextCol)) {
                continue;
            }

            if (isTree(nextRow, nextCol)) {
                count++;
            }
        }

        return count;
    }

    private static List<int[]> getEmpties(int row, int col) {
        List<int[]> empties = new ArrayList<>();

        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (!inRange(nextRow, nextCol)) {
                continue;
            }

            if (map[nextRow][nextCol] == -1 || isTree(nextRow, nextCol) || sprayed[nextRow][nextCol] > 0) {
                continue;
            }

            empties.add(new int[] { nextRow, nextCol });
        }

        return empties;
    }

    private static int countDestroyedTrees(int row, int col) {
        int destroyedCnt = map[row][col];

        for (int[] dir : DIAGONALS) {
            for (int i = 1; i <= k; i++) {
                int nextRow = row + dir[0] * i;
                int nextCol = col + dir[1] * i;

                if (!inRange(nextRow, nextCol) || !isTree(nextRow, nextCol)) {
                    break;
                }

                destroyedCnt += map[nextRow][nextCol];
            }
        }

        return destroyedCnt;
    }

    private static boolean inRange(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

}
