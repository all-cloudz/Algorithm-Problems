package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Problem_Tree1_18 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        HashSet<Integer> numSet = new HashSet<>();
        int n = Integer.parseInt(input.readLine());

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            numSet.add(Integer.parseInt(tokenizer.nextToken()));
        }

        System.out.println(numSet.size());
    }

}
