package problem_1725;

import java.io.*;

public class Problem_1725_SegmentTree {
    private static class MinArgTree{
        private int[] tree;
        private int size;

        public MinArgTree(long[] arr) {
            int heightOfTree = (int) Math.ceil(Math.log(arr.length - 1) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, heightOfTree);

            this.tree = new int[this.size];
            init(arr);
        }

        public void init(long[] arr) {
            init(1, arr, 1, arr.length - 1);
        }

        public int init(int nodeIdx, long[] arr, int left, int right) {
            if (left == right) {
                return tree[nodeIdx] = left;
            }

            int mid = left + (right - left >> 1);

            int idx1 = init(2 * nodeIdx, arr, left, mid);
            int idx2 = init(2 * nodeIdx + 1, arr, mid + 1, right);

            return (arr[idx1] < arr[idx2]) ? (tree[nodeIdx] = idx1) : (tree[nodeIdx] = idx2);
        }

        public int minArg(long[] arr, int left, int right) {
            return minArg(1, 1, arr.length - 1, arr, left, right);
        }

        public int minArg(int nodeIdx, int left, int right, long[] arr, int inf, int sup) {
            if (right < inf || left > sup) {
                return 0;
            }

            if (inf <= left && right <= sup) {
                return tree[nodeIdx];
            }

            int mid = left + (right - left >> 1);

            int idx1 = minArg(2 * nodeIdx, left, mid, arr, inf, sup);
            int idx2 = minArg(2 * nodeIdx + 1, mid + 1, right, arr, inf, sup);

            return (arr[idx1] < arr[idx2]) ? idx1 : idx2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        long[] arr = new long[N + 1];
        arr[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(input.readLine());
        }

        MinArgTree tree = new MinArgTree(arr);
        System.out.println(getMaxArea(arr, tree));
    }

    public static long getMaxArea(long[] arr, MinArgTree tree) {
        return getMaxArea(arr, 1, arr.length - 1, tree);
    }

    public static long getMaxArea(long[] arr, int left, int right, MinArgTree tree) {
        int curMinArg = tree.minArg(arr, left, right);
        long maxArea = (right - left + 1) * arr[curMinArg];

        if (left == right) {
            return maxArea;
        }

        if (left < curMinArg) {
            maxArea = Math.max(maxArea, getMaxArea(arr, left, curMinArg - 1, tree));
        }

        if (curMinArg < right) {
            maxArea = Math.max(maxArea, getMaxArea(arr, curMinArg + 1, right, tree));
        }

        return maxArea;
    }
}
