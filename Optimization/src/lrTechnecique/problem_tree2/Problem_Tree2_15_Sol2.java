package lrTechnecique.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_Tree2_15_Sol2 {

    private static class MaxTree {
        private int[] maxTree;
        private int size;

        public MaxTree(int[] nums) {
            int heightOfTree = (int) Math.ceil((int) Math.log(nums.length) / Math.log(2));
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

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


    }

}
