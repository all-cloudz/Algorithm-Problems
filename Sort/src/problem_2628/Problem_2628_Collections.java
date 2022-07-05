package problem_2628;

import java.io.*;
import java.util.*;

public class Problem_2628_Collections {
    private static List<Integer> cutWidth = new ArrayList<>();
    private static List<Integer> cutHeight = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        cutWidth.add(Integer.parseInt(tokenizer.nextToken()));
        cutHeight.add(Integer.parseInt(tokenizer.nextToken()));
        cutWidth.add(0);
        cutHeight.add(0);

        int num = Integer.parseInt(input.readLine());
        while (num-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());

            if (tokenizer.nextToken().equals("0")) {
                cutHeight.add(Integer.parseInt(tokenizer.nextToken()));
                continue;
            }

            cutWidth.add(Integer.parseInt(tokenizer.nextToken()));
        }

        System.out.print(maxArea());
    }

    private static int maxArea() {
        return max(cutHeight) * max(cutWidth);
    }

    private static int max(List<Integer> cut) {
        Collections.sort(cut);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < cut.size(); i++) {
            max = Math.max(max, cut.get(i) - cut.get(i - 1));
        }

        return max;
    }
}
