package hash.problem_d3_2948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_D3_2948_TwoPointer {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input.readLine();
            String[] from = input.readLine().split(" ");
            String[] to = input.readLine().split(" ");
            answer.append(String.format("#%d %d%n", tc, countCommonString(from.clone(), to.clone())));
        }

        System.out.println(answer);
    }

    private static int countCommonString(String[] from, String[] to) {
        Arrays.sort(from);
        Arrays.sort(to);

        int count = 0;
        int fromPointer = 0;
        int toPointer = 0;

        while (fromPointer < from.length && toPointer < to.length) {
            if (from[fromPointer].equals(to[toPointer])) {
                count++;
                toPointer++;
                fromPointer++;
            } else if (from[fromPointer].compareTo(to[toPointer]) > 0) {
                toPointer++;
            } else {
                fromPointer++;
            }
        }

        return count;
    }

}
