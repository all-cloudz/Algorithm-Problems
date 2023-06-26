package parametric_search.code_자연수_n개의_합;

import java.util.Scanner;

public class Code_자연수_n개의_합 {

    private static final int RIGHT = 100_000;
    private static final long[] prefixSum;

    static {
        prefixSum = new long[RIGHT];
        for (int i = 1; i < RIGHT; i++) {
            prefixSum[i] = i + prefixSum[i - 1];
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int s = input.nextInt();
        System.out.println(findMax(s));
    }

    private static int findMax(int upperBound) {
        int left = 0;
        int right = RIGHT;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (prefixSum[mid] <= upperBound) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }

}
