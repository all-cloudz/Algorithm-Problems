package problem_12015;

import java.util.Scanner;

public class Problem_12015 {
    private static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        System.out.println(binarySearch(A));
    }

    private static int binarySearch(int[] A) {
        int idx = 0;

        int[] B = new int[N];
        B[0] = A[0];

        for (int i = 1; i < N; i++) {
            int left = 0;
            int right = idx;

            if (B[idx] < A[i]) {
                B[++idx] = A[i];
                continue;
            }

            while (left <= right) {
                if (left == right) {
                    if (B[right] > A[i]) {
                        B[right] = A[i];
                    }
                    break;
                }

                int mid = left + (right - left) / 2;

                if (B[mid] >= A[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return idx + 1;
    }
}
