package string.problem_단어가_등장하는_횟수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_단어가_등장하는_횟수_TimeOut_2 {

    private static final int BASE = 31;
    private static final int BITS = (1 << 16) - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String B = input.readLine();
            String S = input.readLine();
            answer.append(String.format("#%d %d%n", tc, countCommonSubstring(B, S)));
        }

        System.out.println(answer);
    }

    public static int countCommonSubstring(String target, String pattern) {
        int targetLen = target.length();
        int patternLen = pattern.length();

        if (targetLen < patternLen) {
            return 0;
        }

        int pow = 1;
        for (int i = 0; i < patternLen; i++) {
            pow *= BASE;
        }

        Node[] nodes = new Node[1 << 16];
        int hash = 0;
        for (int i = 0; i <= targetLen - patternLen; i++) {
            if (i == 0) {
                hash = hashCode(target.substring(0, patternLen));
            } else {
                hash = hash * BASE - target.charAt(i - 1) * pow + target.charAt(i + patternLen - 1);
            }

            int index = hash & BITS;
            nodes[index] = new Node(i, nodes[index]);
        }

        int count = 0;
        int patternHashIndex = hashCode(pattern) & BITS;
        for (Node cur = nodes[patternHashIndex]; cur != null; cur = cur.next) {
            if (isEqual(pattern, target.substring(cur.startIndex, cur.startIndex + patternLen))) {
                count++;
            }
        }

        return count;
    }

    private static int hashCode(String str) {
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = hash * BASE + str.charAt(i);
        }

        return hash;
    }

    private static boolean isEqual(String string, String anotherString) {
        if (string == anotherString) {
            return true;
        }

        if (string.length() != anotherString.length()) {
            return false;
        }

        for (int len = string.length(), i = 0; i < len; i++) {
            if (string.charAt(i) != anotherString.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private static class Node {
        private final int startIndex;
        private final Node next;

        public Node(int startIndex, Node next) {
            this.startIndex = startIndex;
            this.next = next;
        }
    }

}
