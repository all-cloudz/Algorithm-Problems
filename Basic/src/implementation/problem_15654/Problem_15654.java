package implementation.problem_15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_15654 {

    private static int N;
    private static int M;
    private static int[] nums;
    private static int[] selected;
    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        nums = new int[N];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(nums);

        selected = new int[M];
        answer = new StringBuilder();
        selectNums(0, 0);
        System.out.println(answer);
    }

    private static void selectNums(int cnt, int isSelected) {
        if (cnt == M) {
//            answer.append(
//                    Arrays.toString(selected)
//                          .replaceAll("[\\[\\],]", "")
//            ).append("\n");

            for (int i = 0; i < M; i++) {
                answer.append(selected[i]).append(" ");
            }
            answer.append("\n");

            return;
        }

        for (int i = 0; i < N; i++) {
            if ((isSelected & 1 << i) != 0) {
                continue;
            }

            selected[cnt] = nums[i];
            selectNums(cnt + 1, isSelected | 1 << i);
        }
    }

}
