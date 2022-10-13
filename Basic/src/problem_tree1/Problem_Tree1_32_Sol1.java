package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_32_Sol1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Integer> numSet = new TreeSet<>();
        while (n-- > 0) {
            numSet.add(Integer.parseInt(input.readLine()));
        }

        int minDiff = Integer.MAX_VALUE;
        for (int num : numSet) {
            Integer target = numSet.ceiling(num + m);

            if (target != null) {
                minDiff = Math.min(minDiff, target - num);
            }
        }

        if (minDiff == Integer.MAX_VALUE) {
            minDiff = -1;
        }

        System.out.println(minDiff);
    }

}
