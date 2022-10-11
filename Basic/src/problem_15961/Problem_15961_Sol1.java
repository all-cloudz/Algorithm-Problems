package problem_15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_15961_Sol1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int d = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());

        int[] displayed = new int[N];
        for (int i = 0; i < N; i++) {
            displayed[i] = Integer.parseInt(input.readLine());
        }

        int[] eated = new int[d + 1];
        eated[displayed[0]]++;

        int left = 0;
        int right = 1;
        int cnt = 1;
        int maxCnt = Integer.MIN_VALUE;

        while (right < N + k) {
            if (right - left < k) {
                cnt += shiftRight(displayed, eated, right++ % N);
            } else {
                cnt -= shiftLeft(displayed, eated, left++);
            }

            if (right - left == k) {
                maxCnt = Math.max(maxCnt, (eated[c] == 0) ? cnt + 1 : cnt);
            }
        }

        System.out.println(maxCnt);
    }

    private static int shiftRight(int[] displayed, int[] eated, int right) {
        return (eated[displayed[right]]++ == 0) ? 1 : 0;
    }

    private static int shiftLeft(int[] displayed, int[] eated, int left) {
        return (eated[displayed[left]]-- == 1) ? 1 : 0;
    }

}
