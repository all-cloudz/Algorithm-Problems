package problem_14002;

import java.io.*;
import java.util.*;

public class Problem_14002_BinarySearch {
    private static LinkedList<Integer> subSequence = new LinkedList<>();
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        int[] sequence = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }

        findLIS(sequence);

        System.out.println(answer);
    }

    private static void findLIS(int[] sequence) {
        final int N = sequence.length;

        int[] tabulate = new int[N];
        int[] idxs = new int[N];

        tabulate[0] = sequence[0];
        idxs[0] = 0;
        int LISLength = 1;

        for (int i = 1; i < N; i++) {
            int target = sequence[i];

            if (tabulate[LISLength - 1] < target) {
                tabulate[LISLength] = target;
                idxs[i] = LISLength;
                LISLength++;
                continue;
            }

            idxs[i] = bianarySearch_LBD(tabulate, LISLength, target);
            tabulate[idxs[i]] = target;
        }

        int[] max = getMax(idxs);
        answer.append(max[1] + 1).append('\n');

        for (int i = max[0]; i >= 0; i--) {
            if (max[1] >= 0 && idxs[i] == max[1]) {
                subSequence.addFirst(sequence[i]);
                max[1]--;
            }
        }

        while (!subSequence.isEmpty()) {
            answer.append(subSequence.removeFirst()).append(' ');
        }
    }

    private static int bianarySearch_LBD(int[] tabulate, int LISLength, int target) {
        int left = 0;
        int right = LISLength;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (tabulate[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int[] getMax(int[] idxs) {
        int max = Integer.MIN_VALUE;
        int idx = 0;

        for (int i = 0; i < idxs.length; i++) {
            if (idxs[i] > max) {
                max = idxs[i];
                idx = i;
            }
        }

        return new int[] {idx, max};
    }
}
