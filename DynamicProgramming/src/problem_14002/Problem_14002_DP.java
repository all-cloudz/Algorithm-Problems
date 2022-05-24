package problem_14002;

import java.io.*;
import java.util.*;

public class Problem_14002_DP {
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


        for (int i = 0; i < N; i++) {
            tabulate[i] = 1;

            for (int j = 0; j < i; j++) {
                if (sequence[i] > sequence[j]) {
                    tabulate[i] = Math.max(tabulate[j] + 1, tabulate[i]);
                }
            }
        }

        int[] max = getMax(tabulate);
        answer.append(max[1]).append('\n');

        for (int i = max[0]; i >= 0; i--) {
            if (max[1] > 0 && tabulate[i] == max[1]){
                subSequence.addFirst(sequence[i]);
                max[1]--;
            }
        }

        while (!subSequence.isEmpty()) {
            answer.append(subSequence.removeFirst()).append(' ');
        }
    }

    private static int[] getMax(int[] tabulate) {
        int max = Integer.MIN_VALUE;
        int idx = 0;

        for (int i = 0; i < tabulate.length; i++) {
            if (tabulate[i] > max) {
                max = tabulate[i];
                idx = i;
            }
        }

        return new int[] {idx, max};
    }
}
