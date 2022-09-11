package lrTechnecique.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_16_Sol2 {

    private static class MaxTree {
        private int[] maxTree;
        private int size;

        public MaxTree(int[] nums) {
            int heightOfTree = (int) Math.ceil(Math.log(nums.length) / Math.log(2));
            this.size = (int) Math.pow(2, heightOfTree + 1);
            this.maxTree = new int[this.size];
            init(1, 0, nums.length - 1, nums);
        }

        public int init(int node, int left, int right, int[] nums) {
            if (left == right) {
                return maxTree[node] = nums[left];
            }

            int mid = left + (right - left >> 1);
            return maxTree[node] = Math.max(init(2 * node, left, mid, nums), init(2 * node + 1, mid + 1, right, nums));
        }

        public int getMax(int node, int left, int right, int inf, int sup) {
            if (right < inf || left > sup) {
                return 0;
            }

            if (inf <= left && right <= sup) {
                return maxTree[node];
            }

            int mid = left + (right - left >> 1);
            return Math.max(getMax(2 * node, left, mid, inf, sup), getMax(2 * node + 1, mid + 1, right, inf, sup));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        int[] nums = new int[n];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        MaxTree maxTree = new MaxTree(nums);

        while (q-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int left = Integer.parseInt(tokenizer.nextToken()) - 1;
            int right = Integer.parseInt(tokenizer.nextToken()) - 1;

            answer.append(Math.max(
                    maxTree.getMax(1, 0, n - 1, 0, left - 1), maxTree.getMax(1, 0, n - 1, right + 1, n - 1)
            )).append('\n');
        }

        System.out.println(answer);
    }

}
