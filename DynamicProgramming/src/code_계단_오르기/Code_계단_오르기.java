package code_계단_오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Code_계단_오르기 {

    private static int[] cache = new int[1_001];

    static {
        Arrays.fill(cache, -1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());
        climbStairs(N);
        System.out.println(cache[N]);
    }

    private static int climbStairs(int cur) {
        if (cache[cur] != -1) {
            return cache[cur];
        }

        if (cur == 2 || cur == 3) {
            return cache[cur] = 1;
        }

        if (cur <= 1) {
            return 0;
        }

        return cache[cur] = (climbStairs(cur - 2) + climbStairs(cur - 3)) % 10_007;
    }

}
