package string.problem_3033;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_3033 {

    private static class Node {
        private int startIdx;
        private Node next;

        public Node(int startIdx, Node next) {
            this.startIdx = startIdx;
            this.next = next;
        }
    }

    private static final int POW = 31;
    private static final int[] cache;

    static {
        cache = new int[200_001];
        cache[1] = POW;

        for (int i = 2; i < 200_001; i++) {
            cache[i] = cache[i - 1] * POW;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(input.readLine());
        String str = input.readLine();

        int maxLength = binarySearch(str, 0, L);

        if (maxLength == -1) {
            System.out.println(0);
            return;
        }


        System.out.println(maxLength);
    }

    private static int binarySearch(String str, int left, int right) {
        while (left < right) {
            int mid = left + (right - left >> 1);

            if (check(str, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }

    private static boolean check(String str, int mid) {
        Node[] hashToString = new Node[1 << 16];
        int MOD = (1 << 16) - 1;

        int hash = 0;
        for (int i = 0; i < mid; i++) {
            hash = hash * POW + str.charAt(i);
        }
        int hIdx = hash & MOD;
        hashToString[hIdx] = new Node(0, hashToString[hIdx]);

        for (int len = str.length(), i = mid; i < len; i++) {
            hash = hash * POW + str.charAt(i);
            hash -= str.charAt(i - mid) * cache[mid];
            hIdx = hash & MOD;

            if (hashToString[hIdx] == null) {
                hashToString[hIdx] = new Node(i - mid + 1, hashToString[hIdx]);
                continue;
            }

            for (Node node = hashToString[hIdx]; node != null; node = node.next) {
                if (isSame(str, i - mid + 1, node.startIdx, mid)) {
                    return true;
                }
            }


            hashToString[hIdx] = new Node(i - mid + 1, hashToString[hIdx]);
        }

        return false;
    }

    private static boolean isSame(String src, int startIdx1, int startIdx2, int len) {
        for (int i = 0; i < len; i++) {
            if (src.charAt(startIdx1 + i) != src.charAt(startIdx2 + i)) {
                return false;
            }
        }

        return true;
    }

}
