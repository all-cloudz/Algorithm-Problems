package problem_tree1;

import java.io.*;
import java.util.*;

public class Problem_Tree1_10_Sol1 {

    private static class CombiString {
        private String str;

        public CombiString(String str) {
            this.str = str;
        }

        @Override
        public int hashCode() {
            int hash = str.length();

            for (int size = hash, i = 0; i < size; i++) {
                hash += str.charAt(i);
            }

            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof CombiString)) {
                return false;
            }

            CombiString that = (CombiString) o;
            return this.hashCode() == that.hashCode();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Map<CombiString, Integer> cntOfStr = new HashMap<>();
        int n = Integer.parseInt(input.readLine());
        while (n-- > 0) {
            CombiString str = new CombiString(input.readLine());
            cntOfStr.put(str, cntOfStr.getOrDefault(str, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        for (int value : cntOfStr.values()) {
            max = Math.max(max, value);
        }

        System.out.println(max);
    }

}
