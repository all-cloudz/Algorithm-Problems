package problem_1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_1629 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        long A = Long.parseLong(tokenizer.nextToken());
        long B = Long.parseLong(tokenizer.nextToken());
        long C = Long.parseLong(tokenizer.nextToken());

        System.out.println(power(A, B, C));
    }

    private static long power(long A, long B, long C) {
        if (B == 1) {
            return A % C;
        }

        long part = power(A, B >> 1, C);
        long result = part * part % C;

        if ((B & 1) == 1) {
            result = result * A % C;
        }

        return result;
    }

}
