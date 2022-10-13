package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_31 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Integer> numSet = new TreeSet<>();
        for (int i = 1; i <= m; i++) {
            numSet.add(i);
        }

        int cnt = 0;
        tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            int wanted = Integer.parseInt(tokenizer.nextToken());
            Integer seated = numSet.floor(wanted);

            if (seated == null) {
                break;
            }

            cnt++;
            numSet.remove(seated);
        }

        System.out.println(cnt);
    }

}
