package implementation.problem_16935;

import java.io.*;
import java.util.*;

public class Problem_16935 {
    private static int N;
    private static int M;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(input.readLine());
        while (st.hasMoreTokens()) {
            String operation = st.nextToken();
            operate(operation);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer.append(arr[i][j]).append(' ');
            }

            answer.append('\n');
        }

        System.out.println(answer);
    }

    private static void operate(String operation) {
        switch (operation) {
            case "1" :
                flipRow();
                break;
            case "2" :
                flipCol();
                break;
            case "3" :
                rotateRight();
                break;
            case "4" :
                rotateLeft();
                break;
            case "5" :
                moveRight();
                break;
            case "6" :
                moveLeft();
        }
    }

    private static void flipRow() {
        for (int i = 0; i < N / 2; i++) {
            int[] tmp = arr[i];
            arr[i] = arr[N - 1 - i];
            arr[N - 1 - i] = tmp;
        }
    }

    private static void flipCol() {
        for (int j = 0; j < M / 2; j++) {
            for (int i = 0; i < N; i++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[i][M - 1 - j];
                arr[i][M - 1 - j] = tmp;
            }
        }
    }

    private static void rotateRight() {
        int[][] rotated = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rotated[j][N - 1 - i] = arr[i][j];
            }
        }

        int tmp = N;
        N = M;
        M = tmp;

        arr = rotated;
    }

    private static void rotateLeft() {
        int[][] rotated = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rotated[j][i] = arr[i][M - 1 - j];
            }
        }

        int tmp = N;
        N = M;
        M = tmp;

        arr = rotated;
    }

    private static void moveRight() {
        int[][] tmp = new int[N / 2][M / 2];

        for (int i = 0; i < N / 2; i++) {
            System.arraycopy(arr[i], 0, tmp[i], 0, M / 2);
        }

        for (int i = 0; i < N / 2; i++) {
            System.arraycopy(arr[i + N / 2], 0, arr[i], 0, M / 2);
            System.arraycopy(arr[i + N / 2], M / 2, arr[i + N / 2], 0, M / 2);
            System.arraycopy(arr[i], M / 2, arr[i + N / 2], M / 2, M / 2);
            System.arraycopy(tmp[i], 0, arr[i], M / 2, M / 2);
        }
    }

    private static void moveLeft() {
        int[][] tmp = new int[N / 2][M / 2];

        for (int i = 0; i < N / 2; i++) {
            System.arraycopy(arr[i], 0, tmp[i], 0, M / 2);
        }

        for (int i = 0; i < N / 2; i++) {
            System.arraycopy(arr[i], M / 2, arr[i], 0, M / 2);
            System.arraycopy(arr[i + N / 2], M / 2, arr[i], M / 2, M / 2);
            System.arraycopy(arr[i + N / 2], 0, arr[i + N / 2], M / 2, M / 2);
            System.arraycopy(tmp[i], 0, arr[i + N / 2], 0, M / 2);
        }
    }
}
