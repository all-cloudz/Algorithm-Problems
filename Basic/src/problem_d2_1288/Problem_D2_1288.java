package problem_d2_1288;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_D2_1288 {

    private static final int TARGET = (1 << 10) - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(input.readLine());

            int visited = 0; // 등장한 숫자
            for (int count = 1; ;count++) {
                char[] curNum = String.valueOf(count * N).toCharArray();

                for (char cur : curNum) {
                    int num = cur - '0';
                    visited = visited | (1 << num);
                }

                if (visited == TARGET) {
                    answer.append(String.format("#%d %d%n", i, count * N));
                    break;
                }
            }
        }

        System.out.println(answer);
    }

}
