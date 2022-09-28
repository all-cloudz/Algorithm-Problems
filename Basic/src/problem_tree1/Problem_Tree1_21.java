package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_Tree1_21 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = i;
        }

        int[][] change = new int[K][2];
        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            change[i][0] = Integer.parseInt(tokenizer.nextToken());
            change[i][1] = Integer.parseInt(tokenizer.nextToken());
        }


        Map<Integer, Set<Integer>> setOfInt = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            setOfInt.put(i, new HashSet<>());
            setOfInt.get(i).add(i);
        }

        for (int i = 0; i < 3 * K; i++) {
            int[] turn = change[i % K];
            swap(nums, turn[0], turn[1]);
            setOfInt.get(nums[turn[0]]).add(turn[0]);
            setOfInt.get(nums[turn[1]]).add(turn[1]);
        }

        for (int i = 1; i <= N; i++) {
            answer.append(setOfInt.get(i).size()).append('\n');
        }

        System.out.println(answer);
    }

    private static void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

}
