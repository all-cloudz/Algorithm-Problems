package prefixSum.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_6 {

    private static int n;
    private static int[][] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        prefixSum = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] 
                                    + prefixSum[i][j - 1] 
                                    - prefixSum[i - 1][j - 1] 
                                    + Integer.parseInt(tokenizer.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                max = Math.max(max, getMaxArea(i, j));
            }
        }

        System.out.println(max);
    }
    
    private static int getMaxArea(int left, int right) {
        int[] cache = new int[n + 1];
        for (int row = 1; row <= n; row++) {
            int sum = prefixSum[row][right]
                        - prefixSum[row][left - 1]
                        - prefixSum[row - 1][right]
                        + prefixSum[row - 1][left - 1];

            cache[row] = Math.max(sum, cache[row - 1] + sum);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, cache[i]);
        }

        return max;
    }

}
