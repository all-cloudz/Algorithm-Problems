package backtracking.code_특정_조건에_맞게_k개_중에_1개를_n번_뽑기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code_특정_조건에_맞게_k개_중에_1개를_n번_뽑기 {

    private static int K;
    private static int N;
    private static int[] nums;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        chooseNums(0);
        System.out.println(answer);
    }

    private static void chooseNums(int idx) {
        if (idx == N) {
            appendNums();
            return;
        }

        for (int i = 1; i <= K; i++) {
            if (idx >= 2 && isConsecutiveOver3(idx, i)) {
                continue;
            }

            nums[idx] = i;
            chooseNums(idx + 1);
        }
    }

    private static boolean isConsecutiveOver3(int idx, int num) {
        return nums[idx - 2] == num && nums[idx - 1] == num;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        K = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        nums = new int[N];
    }

    private static void appendNums() {
        for (int num : nums) {
            answer.append(num).append(' ');
        }

        answer.append('\n');
    }

}
