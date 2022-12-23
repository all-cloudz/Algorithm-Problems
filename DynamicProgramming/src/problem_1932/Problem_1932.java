package problem_1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        int[][] nums = new int[n][];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(input.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        }

        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                nums[i - 1][j] += Math.max(nums[i][j], nums[i][j + 1]);
            }
        }

        System.out.println(nums[0][0]);
    }

}
