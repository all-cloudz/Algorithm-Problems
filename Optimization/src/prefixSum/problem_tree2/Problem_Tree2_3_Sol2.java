package prefixSum.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_Tree2_3_Sol2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[] nums = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = 1;
        int sum = nums[left];
        int cnt = 0;

        while (true) {
            if (sum == k) {
                cnt++;
            }

            if (sum >= k) {
                sum -= nums[left++];
                continue;
            }

            if (right == n) {
                break;
            }

            sum += nums[right++];
        }

        System.out.println(cnt);
    }

}
