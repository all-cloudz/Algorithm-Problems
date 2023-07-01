package code_수_채우기;

import java.util.Scanner;

public class Code_수_채우기 {

    private static final int[] COINS = { 5, 2 };

    private static int n;

    public static void main(String[] args) {
        init();
        System.out.println(minCount());
    }

    private static void init() {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
    }

    private static int minCount() {
        int min = Integer.MAX_VALUE;
        int fiveCount = 0;

        while (n >= 0) {
            n -= COINS[0];
            int count = ++fiveCount;

            if (n >= 0 && (n & 1) == 0) {
                count += n / COINS[1];
                min = Math.min(min, count);
            }
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        }

        return min;
    }

}
