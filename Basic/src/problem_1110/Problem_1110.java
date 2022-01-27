package problem_1110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_1110 {
    private static int cycle = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        int copy = N;

        do {
            copy = 10 * (copy % 10) + (copy / 10 + copy % 10) % 10;
            cycle++;
        } while (!(copy == N));

        System.out.println(cycle);
    }
}