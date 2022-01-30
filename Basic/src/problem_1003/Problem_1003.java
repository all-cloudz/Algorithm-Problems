package problem_1003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Problem_1003 {
    private static int cntOf0;
    private static int cntOf1;
    private static int[] memoization = new int[41]; // 문제의 조건에서 40 이하의 음이 아닌 정수를 피보나치 함수에 대입한다고 주어짐

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(input.readLine());
        int[] nums = new int[T];
        for (int i = 0; i < T; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        for (int i = 0; i < T; i++) {
            cntOf0 = 0;
            cntOf1 = 0;

            if (nums[i] == 0) {
                cntOf0 = 1;
                cntOf1 = 0;
            } else if (nums[i] == 1) {
                cntOf0 = 0;
                cntOf1 = 1;
            } else {
                cntOf0 = fibonacci(nums[i] - 1);
                cntOf1 = fibonacci(nums[i]);
            }

            output.write(String.valueOf(cntOf0) + " " + String.valueOf(cntOf1) + "\n");
        }

        output.flush();

        input.close();
        output.close();
    }

    private static int fibonacci(int num) {
        if (num == 0) {
            return memoization[num] = 0;
        } else if (num == 1) {
            return memoization[num] = 1;
        } else if (memoization[num] != 0) {
            return memoization[num];
        } else {
            memoization[num] = fibonacci(num - 1) + fibonacci(num - 2);
            return memoization[num];
        }
    }
}
