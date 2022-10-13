package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_32_Sol2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        int[] nums = new int[n];
        TreeSet<Integer> numSet = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input.readLine());
            numSet.add(nums[i]);
        }

        int minDiff = Integer.MAX_VALUE;
        for (int num : nums) {
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
