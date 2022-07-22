package problem_14003;

import java.io.*;
import java.util.*;

// 수열 A에 최대 100만개의 값이 들어올 수 있으므로 시간복잡도 O(nlogn)으로 해결해야 한다.
public class Problem_14003 {
    private static int N;
    private static int[] A;
    private static int[] indexes;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(input.readLine());
        A = new int[N];
        indexes = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int len = findLCA();
        answer.append(len).append('\n');

        Stack<Integer> lca = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (indexes[i] != len - 1) {
                continue;
            }

            lca.push(A[i]);
            len--;
        }

        while (!lca.isEmpty()) {
            answer.append(lca.pop()).append(' ');
        }

        System.out.println(answer);
    }

    private static int findLCA() {
        int[] tabulate = new int[N];
        tabulate[0] = A[0];
        int len = 1;

        for (int i = 1; i < N; i++) {
            if (tabulate[len - 1] < A[i]) {
                tabulate[len] = A[i];
                indexes[i] = len++;
                continue;
            }

            int insertIdx = binarySearch_LBD(tabulate, 0, len, A[i]);
            tabulate[insertIdx] = A[i];
            indexes[i] = insertIdx;
        }

        return len;
    }

    private static int binarySearch_LBD(int[] tabulate, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left >> 1);

            if (tabulate[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }
}
