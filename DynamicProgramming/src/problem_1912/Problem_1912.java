package problem_1912;

import java.io.*;
import java.util.*;

public class Problem_1912 {
    private static int n;
    private static int[] nums;
    private static int[] tabulate;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        nums = new int[n];
        tabulate = new int[n];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.print(maxContiSum());
    }

    private static int maxContiSum() {
        tabulate[0] = nums[0];
        int max = tabulate[0];

        for (int i = 1; i < n; i++) {
            tabulate[i] = Math.max(tabulate[i - 1] + nums[i], nums[i]);
            max = Math.max(max, tabulate[i]);
        }

        return max;
    }
}
