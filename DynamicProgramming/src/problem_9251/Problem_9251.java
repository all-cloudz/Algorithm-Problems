package problem_9251;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem_9251 {
    private static Integer[][] memo;
    private static char[] src;
    private static char[] dest;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        src = input.readLine().toCharArray();
        dest = input.readLine().toCharArray();

        memo = new Integer[src.length][dest.length];

        System.out.print(LCS(src.length - 1, dest.length - 1));
    }

    private static int LCS(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (memo[i][j] == null) {
            if (src[i] == dest[j]) {
                memo[i][j] = LCS(i - 1, j - 1) + 1;
            } else {
                memo[i][j] = Math.max(LCS(i - 1, j), LCS(i, j - 1));
            }
        }

        return memo[i][j];
    }
}