package problem_11279;

import java.io.*;

public class Problem_11279_Implementation {
    private static int[] heap = new int[100_001];
    private static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(input.readLine());
        while (N-- > 0) {
            int x = Integer.parseInt(input.readLine());

            if (x == 0 && size == 0) {
                output.write(String.valueOf(0) + "\n");
                continue;
            }

            if (x == 0) {
                output.write(String.valueOf(poll()) + "\n");
                continue;
            }

            offer(x);
        }

        output.flush();
    }

    private static int poll() {
        if (size == 0) {
            throw new NullPointerException();
        }

        int polled = heap[1];
        heap[1] = heap[size];
        heap[size--] = 0;

        int parentIdx = 1;
        int target = heap[parentIdx];

        while (parentIdx * 2 <= size) {
            int childIdx = parentIdx * 2;
            int child = heap[childIdx];

            int rightChildIdx = parentIdx * 2 + 1;
            int rightChild = heap[rightChildIdx];

            if (rightChildIdx <= size && child < rightChild) {
                childIdx = rightChildIdx;
                child = rightChild;
            }

            if (target >= child) {
                break;
            }

            heap[parentIdx] = child;
            parentIdx = childIdx;
        }

        heap[parentIdx] = target;

        return polled;
    }

    private static void offer(int value) {
        heap[++size] = value;

        int idx = size;
        int target = heap[idx];

        while (idx > 1) {
            int parentIdx = idx / 2;
            int parent = heap[parentIdx];

            if (parent >= target) {
                break;
            }

            heap[idx] = parent;
            idx = parentIdx;
        }

        heap[idx] = target;
    }
}
