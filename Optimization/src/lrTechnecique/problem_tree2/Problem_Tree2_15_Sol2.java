package lrTechnecique.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_15_Sol2 {

    private static class MaxTree {
        private int[] maxTree;
        private int size;

        public MaxTree(int[] nums) {
            int heightOfTree = (int) Math.ceil(Math.log(nums.length) / Math.log(2));
            this.size = (int) Math.pow(2, heightOfTree + 1);
            this.maxTree = new int[size];
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
            if (right < inf || sup < left) {
                return Integer.MIN_VALUE;
            }

            if (inf <= left && right <= sup) {
                return maxTree[node];
            }

            int mid = left + (right - left >> 1);
            return Math.max(getMax(2 * node, left, mid, inf, sup), getMax(2 * node + 1, mid + 1, right, inf, sup));
        }
    }

    private static int N;
    private static int[] nums;
    private static MaxTree maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        nums = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        maxTree = new MaxTree(nums);
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < N - 4; i++) {
            maxVal = Math.max(maxVal, maxTree.getMax(1, 0, N - 1, 0, i) + nums[i + 2] + maxTree.getMax(1, 0, N - 1, i + 4, N - 1));
        }

        System.out.println(maxVal);
    }

}
