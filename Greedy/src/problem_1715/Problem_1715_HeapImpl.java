package problem_1715;

import java.util.Scanner;

public class Problem_1715_HeapImpl {

    private static class MinHeap {
        private int[] minHeap;
        private int size;

        public MinHeap(int N) {
            int heightOfTree = (int) (Math.log(N) / Math.log(2));
            minHeap = new int[(int) Math.pow(2, heightOfTree + 1)];
        }

        public void offer(int value) {
            minHeap[++size] = value;
            shiftUp(size, value);
        }

        private void shiftUp(int child, int target) {
            while (child > 1) {
                int parentValue = minHeap[child >> 1];

                if (target >= parentValue) {
                    break;
                }

                minHeap[child] = parentValue;
                child >>= 1;
            }

            minHeap[child] = target;
        }

        public int poll() {
            int polled = minHeap[1];

            minHeap[1] = minHeap[size];
            minHeap[size--] = 0;

            shiftDown(1, minHeap[1]);
            return polled;
        }

        private void shiftDown(int parent, int target) {
            int child;
            while ((child = parent << 1) <= size) {
                int rightChild = (parent << 1) + 1;

                if (rightChild <= size && minHeap[child] > minHeap[rightChild]) {
                    child = rightChild;
                }

                if (minHeap[child] >= target) {
                    break;
                }

                minHeap[parent] = minHeap[child];
                parent = child;
            }

            minHeap[parent] = target;
        }
    }

    private static int N;
    private static MinHeap minHeap;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        minHeap = new MinHeap(N);
        for (int i = 0; i < N; i++) {
            minHeap.offer(input.nextInt());
        }

        System.out.println(countOfSort());
    }

    private static int countOfSort() {
        int cnt = 0;

        while (minHeap.size > 1) {
            Integer card1 = minHeap.poll();
            Integer card2 = minHeap.poll();

            int tmp = card1 + card2;
            cnt += tmp;
            minHeap.offer(tmp);
        }

        return cnt;
    }

}
