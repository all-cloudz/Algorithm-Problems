package problem_d4_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_D4_1861_Review {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            int[][] rooms = new int[N][N];
            int[][] cache = new int[N][N];

            for (int i = 0; i < N; i++) {
                rooms[i] = Arrays.stream(input.readLine().split(" "))
                                 .mapToInt(Integer::parseInt)
                                 .toArray();
            }

            int max = Integer.MIN_VALUE;
            int departure = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int count = countMovableRooms(rooms, cache, i, j);

                    if (max < count) {
                        max = count;
                        departure = rooms[i][j];
                    }

                    if (max == count) {
                        departure = Math.min(departure, rooms[i][j]);
                    }
                }
            }

            answer.append(String.format("#%d %d %d%n", tc, departure, max));
        }

        System.out.println(answer);
    }

    private static int countMovableRooms(int[][] rooms, int[][] cache, int row, int col) {
        if (cache[row][col] != 0) {
            return cache[row][col];
        }

        cache[row][col] = 1;

        for (int[] move : DIRECTIONS) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (!isIn(nextRow, nextCol)) {
                continue;
            }

            if (rooms[nextRow][nextCol] - rooms[row][col] == 1) {
                cache[row][col] = countMovableRooms(rooms, cache, nextRow, nextCol) + 1;
            }
        }

        return cache[row][col];
    }

    private static boolean isIn(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
    }

}
