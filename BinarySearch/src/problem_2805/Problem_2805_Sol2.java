package problem_2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_2805_Sol2 {

    private static int N;
    private static int M;
    private static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        trees = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxHeight = binarySearch_UBD(0, Arrays.stream(trees).max().getAsInt() + 1);
        System.out.println(maxHeight);
    }

    private static int binarySearch_UBD(int left, int right) {
        while (left < right) {
            int mid = left + (right - left >> 1);

            if (isPossible(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }

    private static boolean isPossible(int mid) {
        long sum = 0;

        for (int tree : trees) {
            sum += Math.max(tree - mid, 0L);
        }

        return sum >= M;
    }

}
