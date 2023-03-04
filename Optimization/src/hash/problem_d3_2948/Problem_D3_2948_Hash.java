package hash.problem_d3_2948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_D3_2948_Hash {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());

            Set<String> strings = new HashSet<>();
            tokenizer = new StringTokenizer(input.readLine());
            while (N-- > 0) {
                strings.add(tokenizer.nextToken());
            }

            int count = 0;
            tokenizer = new StringTokenizer(input.readLine());
            while (M-- > 0) {
                if (strings.contains(tokenizer.nextToken())) {
                    count++;
                }
            }

            answer.append(String.format("#%d %d%n", tc, count));
        }

        System.out.println(answer);
    }

}
