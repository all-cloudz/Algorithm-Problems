package problem_d3_10726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_10726 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer.append('#').append(tc).append(' ');

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());

            int checker = (1 << N) - 1;
            if ((M & checker) == checker) {
                answer.append("ON").append('\n');
                continue;
            }

            answer.append("OFF").append('\n');
        }

        System.out.println(answer);
    }

}
