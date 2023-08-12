package Boj_1758;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Boj_1758 {

    private static int N;
    private static Long[] customers;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calculateTip());
    }

    private static long calculateTip() {
        Arrays.sort(customers, Comparator.reverseOrder());
        long sum = 0;

        for (int i = 0; i < N; i++) {
            final long tip = customers[i] - i;
            if (tip < 0) {
                break;
            }

            sum += tip;
        }

        return sum;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());

        customers = new Long[N];
        for (int i = 0; i < N; i++) {
            customers[i] = Long.valueOf(input.readLine());
        }
    }
}
