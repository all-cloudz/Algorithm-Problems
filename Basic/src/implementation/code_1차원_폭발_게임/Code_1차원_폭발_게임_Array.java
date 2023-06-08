package implementation.code_1차원_폭발_게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code_1차원_폭발_게임_Array {

    private static int N;
    private static int M;
    private static int[] bombs;

    public static void main(String[] args) throws IOException {
        init();
        while (boom());

        StringBuilder answer = new StringBuilder();
        answer.append(N).append('\n');
        for (int i = 0; i < N; i++) {
            answer.append(bombs[i]).append('\n');
        }
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        bombs = new int[N];
        for (int i = 0; i < N; i++) {
            bombs[i] = Integer.parseInt(input.readLine());
        }
    }

    private static boolean boom() {
        boolean exploded = false;
        int[] tmp = bombs.clone();

        int left = 0;
        while (left < N) {
            int right = getRight(left);

            if (right - left >= M) {
                markRemovedBombs(tmp, left, right);
                exploded = true;
            }

            left = right;
        }

        copy(tmp, bombs);
        return exploded;
    }

    private static int getRight(int left) {
        int idx = left;

        while (idx < N && bombs[left] == bombs[idx]) {
            idx++;
        }

        return idx;
    }

    private static void markRemovedBombs(int[] tmp, int left, int right) {
        for (int i = left; i < right; i++) {
            tmp[i] = -1;
        }
    }

    private static void copy(int[] tmp, int[] bombs) {
        int count = 0;
        int idx = 0;

        while (idx < N) {
            if (tmp[idx] != -1) {
                bombs[count++] = tmp[idx];
            }

            idx++;
        }

        N = count;
    }

}
