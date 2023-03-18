package parametric_search.problem_d5_10507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D5_10507 {

    private static int n;
    private static int p;
    private static int[] studied;
    private static int[] unstudied;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            p = Integer.parseInt(tokenizer.nextToken());

            studied = new int[n + 1];
            unstudied = new int[n + 1];
            tokenizer = new StringTokenizer(input.readLine());
            for (int i = 1; i <= n; i++) {
                studied[i] = Integer.parseInt(tokenizer.nextToken());
                unstudied[i] = unstudied[i - 1] + (studied[i] - studied[i - 1] - 1);
            }

            answer.append(String.format("#%d %d%n", tc, findMaxLength()));
        }

        System.out.println(answer);
    }

    private static int findMaxLength() {
        int left = 1;
        int right = 1;
        int maxLength = 0;

        while (right <= n) {
            while (right <= n && unstudied[right] - unstudied[left] <= p) {
                right++;
            }

            int between = studied[right - 1] - studied[left] + 1;
            int remain = p - (unstudied[right - 1] - unstudied[left]);
            maxLength = Math.max(maxLength, between + remain);

            while (right <= n && left < right && unstudied[right] - unstudied[left] > p) {
                left++;
            }
        }

        return maxLength;
    }

}
