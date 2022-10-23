package problem_6236;

import java.util.Scanner;

public class Problem_6236 {

    private static int N;
    private static int M;
    private static int[] dayMoney;
    private static int max;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();

        dayMoney = new int[N];
        for (int i = 0; i < dayMoney.length; i++) {
            dayMoney[i] = input.nextInt();
            max = Math.max(max, dayMoney[i]);
        }

        System.out.println(binarySearch());
    }

    public static int binarySearch() {
        if (N == M) {
            return max;
        }

        int left = max;
        int right = 10000 * 100000;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (drawMoneyCnt(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static boolean drawMoneyCnt(int target) {
        int cnt = 1;
        int sum = 0;

        for (int len = dayMoney.length, i = 0; i < len; i++) {
            sum += dayMoney[i];

            if (sum > target) {
                cnt++;
                sum = dayMoney[i];
            }
        }

        return cnt <= M;
    }

}
