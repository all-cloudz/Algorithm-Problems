package lrTechnecique.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Problem_Tree2_14_Sol2 {

    private static final Map<String, Integer> HSP;

    private static int N;
    private static int[] B;
    private static int[] leftSum;
    private static int[] rightSum;

    static {
        HSP = new HashMap<>();
        HSP.put("H", 0);
        HSP.put("S", 1);
        HSP.put("P", 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        B = new int[N];
        leftSum = new int[N];
        rightSum = new int[N + 1];

        for (int i = 0; i < N; i++) {
            B[i] = HSP.get(input.readLine());
        }

        for (int i = 0; i < 3; i++) {
            for (int cnt = 0, j = 0; j < N; j++) {
                if ((i + 1) % 3 == B[j]) {
                    cnt++;
                }

                leftSum[j] = Math.max(leftSum[j], cnt);
            }

            for (int cnt = 0, j = N - 1; j >= 0; j--) {
                if ((i + 1) % 3 == B[j]) {
                    cnt++;
                }

                rightSum[j] = Math.max(rightSum[j], cnt);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, leftSum[i] + rightSum[i + 1]);
        }

        System.out.println(max);
    }

}
