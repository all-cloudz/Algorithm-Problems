package problem_2243;

import java.io.*;
import java.util.*;

public class Problem_2243 {
    private static class SegmentTree {
        private int[] segTree;

        public SegmentTree(int arrLength) {
            int height = (int) Math.ceil(Math.log(arrLength) / Math.log(2));
            segTree = new int[(int) Math.pow(2, height + 1) * 2];
        }

        public int get(int node) {
            return segTree[node];
        }

        /** @param nums segTree를 생성하기 위한 기반이 되는 배열
         * @param node segTree에서 현재 방문한 노드(index)
         * @param start node에 저장할 배열의 구간 합의 시작 index
         * @param end node에 저장할 배열의 구간 합의 끝 index */
        public int init(int[] nums, int node, int start, int end) {
            if (start == end) {
                return segTree[node] = nums[start];
            }

            int mid = start + (end - start >> 1);
            return segTree[node] = init(nums, node * 2, start, mid) + init(nums, node * 2 + 1, mid + 1, end);
        }

        /** @param node segTree에서 현재 방문한 노드(index)
         * @param start node에 저장한 배열의 구간 합의 시작 index
         * @param end node에 저장할 배열의 구간 합의 끝 index
         * @param index 본래의 배열에서 수정한 요소의 index
         * @param diff 본래의 배열에서 수정 전 요소와 수정 후 요소의 차이 */
        public void update(int node, int start, int end, int index, int diff) {
            if (index < start || end < index) {
                return;
            }

            segTree[node] += diff;

            int mid = start + (end - start >> 1);
            if (start < end) {
                update(node * 2, start, mid, index, diff);
                update(node * 2 + 1, mid + 1, end, index, diff);
            }
        }

        public int changeValue(int node, int start, int end, int index, int changedValue) {
            if (index < start || end < index) {
                return segTree[node];
            }

            if (start == index && index == end) {
                return segTree[node] = changedValue;
            }

            int mid = start + (end - start >> 1);
            return segTree[node] = changeValue(node * 2, start, mid, index, changedValue) + changeValue(node * 2 + 1, mid + 1, end, index, changedValue);
        }

        public int sum(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return 0;
            }

            if (left <= start && end <= right) {
                return segTree[node];
            }

            int mid = start + (end - start >> 1);
            return sum(node * 2, start, mid, left, right) + sum (node * 2 + 1, mid + 1, end, left, right);
        }
    }

    private static int arrLength = 1_000_001;
    private static StringBuilder answer = new StringBuilder();
    private static SegmentTree tree = new SegmentTree(arrLength);

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        while (N-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            final int A = Integer.parseInt(tokenizer.nextToken());
            final int B = Integer.parseInt(tokenizer.nextToken());

            switch (A) {
                case 1 :
                    answer.append(binarySearch(1, 1, arrLength, B)).append('\n');
                    break;
                case 2 :
                    final int C = Integer.parseInt(tokenizer.nextToken());
                    tree.update(1, 1, arrLength, B, C);
            }
        }

        System.out.println(answer);
    }

//    private static int binarySearch(int node, int left, int right, int target) {
//        if (left == right) {
//            tree.update(1, 1, arrLength, left, -1);
//            return left;
//        }
//
//        int mid = left + (right - left >> 1);
//
//        if (target <= tree.get(node * 2)) {
//            return binarySearch(node * 2, left, mid, target);
//        }
//
//        return binarySearch(node * 2 + 1, mid + 1, right, target - tree.get(node * 2));
//    }

    private static int binarySearch(int node, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left >> 1);

            if (target <= tree.get(node * 2)) {
                node = node * 2;
                right = mid;
            } else {
                target -= tree.get(node * 2);
                node = node * 2 + 1;
                left = mid + 1;
            }
        }

        tree.update(1, 1, arrLength, left, -1);
        return left;
    }
}
