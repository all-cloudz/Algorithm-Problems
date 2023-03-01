package math.problem_d5_9843;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_D5_9843_Sol1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(input.readLine());
            output.write(String.format("#%d %d%n", tc, findHeight(N)));
        }
        output.flush();

        input.close();
        output.close();
    }

    public static long findHeight(long totalCount) {
        long discriminant = (totalCount << 3) + 1;
        long sqrt = (long) Math.sqrt(discriminant);

        if (discriminant != sqrt * sqrt || ((sqrt - 1) & 1) != 0) {
            return -1;
        }

        return (sqrt - 1) >> 1;
    }

}
