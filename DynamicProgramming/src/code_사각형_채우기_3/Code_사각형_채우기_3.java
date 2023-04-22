package code_사각형_채우기_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Code_사각형_채우기_3 {

    public static final long DIVISOR = 1_000_000_007;

    /**
     * cache[i][0] := i열까지의 모든 사각형이 채워진 경우
     * cache[i][1] := (1, i)를 제외한 i열까지의 모든 사각형이 채워진 경우
     * cache[i][2] := (2, i)를 제외한 i열까지의 모든 사각형이 채워진 경우
     */
    private static long[][] cache = new long[1_001][3];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());

        cache[0][0] = 1L;
        cache[1][0] = 2L;
        cache[1][1] = 1L;
        cache[1][2] = 1L;
        for (int i = 2; i <= N; i++) {
            cache[i][0] = ((cache[i - 1][0] << 1) + cache[i - 1][1] + cache[i - 1][2] + cache[i - 2][0]) % DIVISOR;
            cache[i][1] = (cache[i - 1][0] + cache[i - 1][2]) % DIVISOR;
            cache[i][2] = (cache[i - 1][0] + cache[i - 1][1]) % DIVISOR;
        }

        System.out.println(cache[N][0]);
    }

}
