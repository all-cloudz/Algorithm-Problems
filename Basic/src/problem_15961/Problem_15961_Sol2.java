package problem_15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_15961_Sol2 {

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
        eated[c]++;
        int cnt = 1;

        for (int i = 0; i < k; i++) {
            if (eated[displayed[i]]++ == 0) {
                cnt++;
            }
        }

        int maxCnt = Integer.MIN_VALUE;
        for (int left = 0, right = k; left < N; left++, right = (right + 1) % N) {
            maxCnt = Math.max(maxCnt, cnt);

            if (eated[displayed[left]]-- == 1) {
                cnt--;
            }

            if (eated[displayed[right]]++ == 0) {
                cnt++;
            }
        }

        System.out.println(maxCnt);
    }

}
