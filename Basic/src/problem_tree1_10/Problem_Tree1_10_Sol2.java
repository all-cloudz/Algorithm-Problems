package problem_tree1_10;

import java.io.*;
import java.util.*;

public class Problem_Tree1_10_Sol2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> cntOfStr = new HashMap<>();
        int n = Integer.parseInt(input.readLine());
        while (n-- > 0) {
            char[] chars = input.readLine().toCharArray();
            Arrays.sort(chars);

            String str = new String(chars);
            cntOfStr.put(str, cntOfStr.getOrDefault(str, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        for (int value : cntOfStr.values()) {
            max = Math.max(max, value);
        }

        System.out.println(max);
    }

}
