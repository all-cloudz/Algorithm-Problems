package problem_tree1_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 네 수의 합 = 두 수의 합의 두 수의 합
public class Problem_Tree1_9 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        int[][] sequences = new int[4][n];

        StringTokenizer tokenizer1 = new StringTokenizer(input.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            sequences[0][i] = Integer.parseInt(tokenizer1.nextToken());
            sequences[1][i] = Integer.parseInt(tokenizer2.nextToken());
        }

        Map<Integer, Integer> cntOfSum = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = sequences[0][i] + sequences[1][j];
                cntOfSum.put(sum, cntOfSum.getOrDefault(sum, 0) + 1);
            }
        }

        tokenizer1 = new StringTokenizer(input.readLine());
        tokenizer2 = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            sequences[2][i] = Integer.parseInt(tokenizer1.nextToken());
            sequences[3][i] = Integer.parseInt(tokenizer2.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += cntOfSum.getOrDefault(-sequences[2][i] - sequences[3][j], 0);
            }
        }
        System.out.println(answer);
    }

}
