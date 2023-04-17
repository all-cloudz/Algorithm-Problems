package code_사각형_채우기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Code_사각형_채우기_2 {

    public static final int DIVISOR = 10_007;

    private static int[] cache = new int[1_001];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        cache[1] = 1;
        cache[2] = 3;
        for (int i = 3; i <= N; i++) {
            cache[i] = (cache[i - 1] + cache[i - 2] * 2) % DIVISOR;
        }

        System.out.println(cache[N]);
    }

}
