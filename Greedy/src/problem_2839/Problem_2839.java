package problem_2839;

import java.io.*;

public class Problem_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        int cnt = N / 5;
        N %= 5;

        while (cnt >= 0) {
            if (N % 3 == 0) {
                cnt += N / 3;
                System.out.println(cnt);
                return;
            }

            cnt--;
            N += 5;
        }

        System.out.println(-1);
    }
}
