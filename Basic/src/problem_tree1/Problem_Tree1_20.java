package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_Tree1_20 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        final int A = Integer.parseInt(tokenizer.nextToken());
        final int B = Integer.parseInt(tokenizer.nextToken());

        Set<Integer> minusSet = new HashSet<>();

        tokenizer = new StringTokenizer(input.readLine());
        for(int i = 0; i < A; i++) {
            minusSet.add(Integer.parseInt(tokenizer.nextToken()));
        }

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < B; i++) {
            int cur = Integer.parseInt(tokenizer.nextToken());

            if (minusSet.contains(cur)) {
                minusSet.remove(cur);
                continue;
            }

            minusSet.add(cur);
        }

        System.out.println(minusSet.size());
    }

}
