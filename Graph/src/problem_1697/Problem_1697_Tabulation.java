package problem_1697;

import java.io.*;
import java.util.*;

public class Problem_1697_Tabulation {
    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(findTarget());
    }

    private static int findTarget() {
        if (K <= N) {
            return N - K;
        }

        int[] tabulated = new int[K + 1]; // N으로부터의 최단 거리

        for (int i = 0; i <= N; i++) {
            tabulated[i] = N - i;
        }

        for (int i = N + 1; i <= K; i++) {
            if (i % 2 == 0) {
                tabulated[i] = Math.min(tabulated[i >> 1], tabulated[i - 1]) + 1;
                continue;
            }

            tabulated[i] = Math.min(tabulated[(i - 1) >> 1], tabulated[(i + 1) >> 1]) + 2;
            tabulated[i] = Math.min(tabulated[i], tabulated[i - 1] + 1);
        }

        return tabulated[K];
    }
}
