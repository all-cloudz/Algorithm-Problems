package problem_24416;

import java.util.*;

public class Problem_24416 {
    private static int cnt1;
    private static int cnt2;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        fib(n);
        fibonacci(n);

        System.out.println(String.format("%d %d", cnt1, cnt2));
    }

    // 사실 a1 = 1, a2 = 1이면 cnt1 = an 이므로 성능 향상을 위해 memoize[n]을 출력해도 된다.
    private static int fib(int n) {
        if (n == 1 || n == 2) {
            cnt1++;
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    private static int fibonacci(int n) {
        int[] memoize = new int[n + 1];

        return fibonacci(n, memoize);
    }

    private static int fibonacci(int n, int[] memoize) {
        if (n == 1 || n == 2) {
            memoize[n] = 1;
            return memoize[n];
        }

        if (memoize[n] != 0) {
            return memoize[n];
        }

        cnt2++;
        memoize[n] = fibonacci(n - 1, memoize) + fibonacci(n - 2, memoize);

        return memoize[n];
    }
}
