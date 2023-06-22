package backtracking.code_k개_중에_1개를_n번_뽑기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code_k개_중에_1개를_n번_뽑기 {

    private static final StringBuilder answer = new StringBuilder();
    private static int K;
    private static int N;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        printPermutation(0);
        System.out.println(answer);
    }

    private static void printPermutation(int idx) {
        if (idx == N) {
            printNums();
            return;
        }

        for (int i = 1; i <= K; i++) {
            nums[idx] = i;
            printPermutation(idx + 1);
        }
    }

    private static void printNums() {
        for (int num : nums) {
            answer.append(num).append(' ');
        }

        answer.append('\n');
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        K = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        nums =  new int[N];
    }

}
