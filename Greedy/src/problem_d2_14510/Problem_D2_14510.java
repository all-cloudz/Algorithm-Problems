package problem_d2_14510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D2_14510 {

    private static int N;
    private static int[] trees;
    private static int[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        table = new int[2001];
        memoize(2000);

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            trees = new int[N];

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int maxHeight = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(tokenizer.nextToken());
                maxHeight = Math.max(maxHeight, trees[i]);
            }

            int[] countOfNum = new int[2];
            for (int tree : trees) {
                int diff = maxHeight - tree;
                countOfNum[0] += diff % 2;
                countOfNum[1] += diff / 2;
            }

            answer.append(String.format("#%d %d%n", tc, getMinDays(countOfNum)));
        }

        System.out.println(answer);
    }

    private static int memoize(int cur) {
        if (table[cur] != 0) {
            return table[cur];
        }

        if (cur == 1) {
            return table[cur] = 2;
        }

        if (cur == 2) {
            return table[cur] = 3;
        }

        return table[cur] = Math.min(memoize(cur - 1) + 2, memoize(cur - 2) + 3);
    }

    private static int getMinDays(int[] countOfNum) {
        balance(countOfNum);
        int minDays = Math.min(countOfNum[0], countOfNum[1]) << 1;

        if (countOfNum[0] > countOfNum[1]) {
            minDays += Math.max((countOfNum[0] - countOfNum[1] << 1) - 1, 0);
        } else if (countOfNum[0] < countOfNum[1]) {
            minDays += table[countOfNum[1] - countOfNum[0]];
        }

        return minDays;
    }

    private static void balance(int[] countOfNum) {
        int diff = Math.max(countOfNum[1] - countOfNum[0], 0) / 3;
        countOfNum[0] += diff << 1;
        countOfNum[1] -= diff;
    }

}
