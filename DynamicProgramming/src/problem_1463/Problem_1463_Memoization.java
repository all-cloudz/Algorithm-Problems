package problem_1463;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_1463_Memoization {

    private static int X;
    private static int[] memoized;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        X = input.nextInt();

        memoized = new int[X + 1];
        Arrays.fill(memoized, 1_000_001);

        operate(X);
        System.out.println(memoized[X]);
    }

    private static int operate(int cur) {
        if (memoized[cur] != 1_000_001) {
            return memoized[cur];
        }

        if (cur == 1) {
            return memoized[cur] = 0;
        }

        if (cur % 3 == 0) {
            memoized[cur] = operate(cur / 3) + 1;
        }

        if (cur % 2 == 0) {
            memoized[cur] = Math.min(memoized[cur], operate(cur / 2) + 1);
        }

        return memoized[cur] = Math.min(memoized[cur], operate(cur - 1) + 1);
    }

}
