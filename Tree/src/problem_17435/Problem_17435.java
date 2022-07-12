package problem_17435;

import java.io.*;
import java.util.*;

// Tree에 대한 문제는 아니고, LCA를 빠르게 찾기 위해 사용되는 '희소 배열(Spares Table)'을 학습할 수 있는 문제
public class Problem_17435 {
    private static StringBuilder answer = new StringBuilder();
    private static int[][] function;
    private static int ceil = (int) (Math.log(500_000) / Math.log(2)) + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(input.readLine());
        function = new int[m + 1][ceil];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= m; i++) {
            function[i][0] = Integer.parseInt(tokenizer.nextToken());
        }

        fillFunction();

        int Q = Integer.parseInt(input.readLine());
        while (Q-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());

            answer.append(composeFunction(n, x)).append('\n');
        }

        System.out.print(answer);
    }

    private static void fillFunction() {
        for (int i = 1; i < ceil; i++) {
            for (int j = 1; j < function.length; j++) {
                function[j][i] = function[function[j][i - 1]][i - 1];
            }
        }
    }

    private static int composeFunction(int n, int x) {
        for (int i = 0; i < ceil; i++) {
            int jump = 1 << i;

            if (n >= jump) {
                x = function[x][i];
                n -= jump;
            }
            // if ((n & jump) != 0) x = function[x][i]; 라 해도 무관하다.
        }

        return x;
    }
}
