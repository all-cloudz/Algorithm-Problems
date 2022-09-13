package lrTechnecique.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_Tree2_14_Sol1 {

    private static final char[] HSP = { 'H', 'S', 'P' };

    private static int N;
    private static char[] B;
    private static int[][] leftSum;
    private static int[][] rightSum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        B = new char[N + 2];
        leftSum = new int[3][N + 2];
        rightSum = new int[3][N + 2];

        for (int i = 1; i <= N; i++) {
            B[i] = input.readLine().charAt(0);
        }

        for (int i = 0; i < 3; i++) {
            char A = HSP[i];

            for (int j = 1; j <= N; j++) {
                leftSum[i][j] = leftSum[i][j - 1] + play(A, B[j]);
            }

            for (int j = N; j >= 1; j--) {
                rightSum[i][j] = rightSum[i][j + 1] + play(A, B[j]);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= N; j++) {
                int other1 = (i + 1) % 3;
                int other2 = (other1 + 1) % 3;

                max = Math.max(max, leftSum[i][j] + rightSum[other1][j + 1]);
                max = Math.max(max, leftSum[i][j] + rightSum[other2][j + 1]);
            }
        }

        System.out.println(max);
    }

    private static int play(char A, char B) {
        if ((A == 'H' && B == 'S') || (A == 'S' && B == 'P') || (A == 'P' && B == 'H')) {
            return 1;
        }

        return 0;
    }

}
