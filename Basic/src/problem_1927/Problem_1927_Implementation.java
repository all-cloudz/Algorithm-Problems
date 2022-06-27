package problem_1927;

import java.io.*;

public class Problem_1927_Implementation {
    private static StringBuilder answer = new StringBuilder();

    private static int[] nums = new int[100_001];
    private static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        while (N-- > 0) {
            int x = Integer.parseInt(input.readLine());

            if (x == 0 && size == 0) {
                answer.append("0\n");
                continue;
            }

            if (x == 0) {
                answer.append(poll()).append('\n');
                continue;
            }

            offer(x);
        }

        System.out.print(answer);
    }

    private static int poll() {
        if (size == 0) {
            throw new NullPointerException();
        }

        int polled = nums[1];
        nums[1] = nums[size];
        nums[size--] = 0;

        shiftDown(1, nums[1]);

        return polled;
    }

    private static void shiftDown(int parentIdx, int target) {
        int childIdx;
        while ((childIdx = parentIdx * 2) <= size) {
            int child = nums[childIdx];

            int rightChildIdx = parentIdx * 2 + 1;
            int rightChild = nums[rightChildIdx];

            if (rightChildIdx <= size && child > rightChild) {
                childIdx = rightChildIdx;
                child = rightChild;
            }

            if (target <= child) {
                break;
            }

            nums[parentIdx] = child;
            parentIdx = childIdx;
        }

        nums[parentIdx] = target;
    }

    private static void offer(int value) {
        nums[++size] = value;
        shiftUp(size, nums[size]);
    }

    private static void shiftUp(int childIdx, int target) {
        while (childIdx > 1) {
            int parentIdx = childIdx / 2;
            int parent = nums[parentIdx];

            if (parent <= target) {
                break;
            }

            nums[childIdx] = parent;
            childIdx = parentIdx;
        }

        nums[childIdx] = target;
    }
}
