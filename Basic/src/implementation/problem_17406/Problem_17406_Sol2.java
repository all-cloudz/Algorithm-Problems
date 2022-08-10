package implementation.problem_17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_17406_Sol2 {
    private static int answer = Integer.MAX_VALUE;

    private static int N;
    private static int M;
    private static int K;
    private static int[][] arr;
    private static int[][] operation;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operation = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(input.readLine());
            operation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            operation[i][1] = Integer.parseInt(st.nextToken()) - 1;
            operation[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] selected = new int[K][3];
        boolean[] isSelected = new boolean[K];
        permutate(selected, isSelected, 0);

        System.out.println(answer);
    }

    private static void permutate(int[][] selected, boolean[] isSelected, int cnt) {
        if (cnt == K) {
            int[][] rotated = new int[N][M];

            for (int i = 0; i < N; i++) {
                System.arraycopy(arr[i], 0, rotated[i], 0, M);
            }

            for (int i = 0; i < K; i++) {
                rotate(rotated, selected[i]);
            }

            for (int i = 0; i < N; i++) {
                int sum = 0;

                for (int j = 0; j < M; j++) {
                    sum += rotated[i][j];
                }

                answer = Math.min(answer, sum);
            }

            return;
        }

        for (int i = 0; i < K; i++) {
            if (isSelected[i]) {
                continue;
            }

            selected[cnt] = operation[i];
            isSelected[i] = true;
            permutate(selected, isSelected, cnt + 1);
            isSelected[i] = false;
        }
    }

    private static void rotate(int[][] rotated, int[] operation) {
        final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        for (int i = 0; i < operation[2]; i++) {
            int curRow = operation[0] - operation[2] + i;
            int curCol = operation[1] - operation[2] + i;

            int tmp = rotated[curRow][curCol];

            int idx = 0;
            while (idx < 4) {
                int prevRow = curRow + DIRECTIONS[idx][0];
                int prevCol = curCol + DIRECTIONS[idx][1];

                if (!isExist(prevRow, prevCol, operation, i)) {
                    idx++;
                    continue;
                }

                rotated[curRow][curCol] = rotated[prevRow][prevCol];
                curRow = prevRow;
                curCol = prevCol;
            }

            rotated[curRow][curCol + 1] = tmp;
        }
    }

    private static boolean isExist(int prevRow, int prevCol, int[] operation, int limit) {
        int supRow = operation[0] + operation[2] - limit;
        int infRow = operation[0] - operation[2] + limit;
        int supCol = operation[1] + operation[2] - limit;
        int infCol = operation[1] - operation[2] + limit;

        return infRow <= prevRow && prevRow <= supRow && infCol <= prevCol && prevCol <= supCol;
    }
}
