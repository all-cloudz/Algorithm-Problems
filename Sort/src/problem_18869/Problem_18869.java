package problem_18869;

import java.io.*;
import java.util.*;

public class Problem_18869 {

    private static int N, M;
    private static int[][] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        nums = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                nums[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.sort(nums[i]);
        }

        int answer = 0;
        for (int i = 1; i < N; i++) {
            int cnt = 0;

            loop : for (int j = 0; j < i; j++) {
                for (int idx = 1; idx < M; idx++) {
                    if ((nums[i][idx - 1] < nums[i][idx]) != (nums[j][idx - 1] < nums[j][idx])) {
                        continue loop;
                    }
                }

                cnt++;
            }

            answer += cnt * (cnt - 1) >> 1;
        }

        System.out.println(answer);
    }

}
