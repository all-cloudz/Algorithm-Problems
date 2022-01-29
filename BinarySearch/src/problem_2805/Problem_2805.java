package problem_2805;

import java.util.Scanner;

public class Problem_2805 {
    private static int N;
    private static int M;
    private static int[] trees;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = sc.nextInt();
        }

        int maxheight = 0;
        for (int tree : trees) {
            if (tree > maxheight) {
                maxheight = tree;
            }
        }

        System.out.println(binarySearch(0, maxheight));
    }

    private static int binarySearch(int left, int right) {
        int mid = left + (right - left) / 2;

        // 종료 조건 1
        if (right - left <= 1) {
            // 종료 조건 2
            if(isHeight(right)) {
                return right;
            }
            return left;
        }

        if (isHeight(mid)) {
            return binarySearch(mid, right);
        } else {
            return binarySearch(left, mid - 1);
        }
    }

    private static boolean isHeight(int height) {
        long sum = 0;

        for (int i = N - 1; i >= 0; i--) {
            if (trees[i] <= height) {
                continue;
            }

            sum += trees[i] - height;
        }

        if (sum >= M) {
            return true;
        }

        return false;
    }
}
