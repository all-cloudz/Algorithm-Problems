package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_Tree1_22 {

    private static int N;
    private static String[] A;
    private static String[] B;

    private static int M;
    private static int[] selected;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        A = new String[N];
        B = new String[N];

        for (int i = 0; i < N; i++) {
            A[i] = input.readLine();
        }

        for (int i = 0; i < N; i++) {
            B[i] = input.readLine();
        }

        M = Integer.parseInt(tokenizer.nextToken());
        selected = new int[3];

        combine(0, 0);
        System.out.println(answer);
    }

    private static void combine(int start, int cnt) {
        if (cnt == 3) {
            if (isDistinct()) {
                answer++;
            }

            return;
        }

        for (int i = start; i < M; i++) {
            selected[cnt] = i;
            combine(i + 1, cnt + 1);
        }
    }

    private static boolean isDistinct() {
        Set<String> selectors = new HashSet<>();

        for (int i = 0; i < N; i++) {
            char A0 = A[i].charAt(selected[0]);
            char A1 = A[i].charAt(selected[1]);
            char A2 = A[i].charAt(selected[2]);

            selectors.add("" + A0 + A1 + A2);
        }

        for (int i = 0; i < N; i++) {
            char B0 = B[i].charAt(selected[0]);
            char B1 = B[i].charAt(selected[1]);
            char B2 = B[i].charAt(selected[2]);

            if (selectors.contains("" + B0 + B1 + B2)) {
                return false;
            }
        }

        return true;
    }

}
