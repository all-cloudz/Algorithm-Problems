package problem_1912;

import java.io.*;
import java.util.*;

public class Problem_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        int[] tabulate = new int[n];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        tabulate[0] = Integer.parseInt(tokenizer.nextToken());
        int max = tabulate[0];

        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            tabulate[i] = Math.max(tabulate[i - 1] + num, num);
            max = Math.max(max, tabulate[i]);
        }

        System.out.print(max);
    }
}
