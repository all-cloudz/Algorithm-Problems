package problem_1806;

import java.io.*;
import java.util.*;

public class Problem_1806 {
    private static int N;
    private static int[] nums;
    private static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMinLength());
    }

    private static int getMinLength() {
        int answer = Integer.MAX_VALUE;

        int sum = 0;
        int left = 0;
        int right = 0;

        while (right <= N) {
            if (sum < S) {
                sum += nums[right++];
                continue;
            }

            answer = Math.min(answer, right - left);
            sum -= nums[left++];
        }

        if (answer == Integer.MAX_VALUE) {
            return 0;
        }

        return answer;
    }
}
