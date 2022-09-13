package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_Tree1_17 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Set<Integer> seq1 = new HashSet<>();
        int n = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            seq1.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int m = Integer.parseInt(input.readLine());
        int[] seq2 = new int[m];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            seq2[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < m; i++) {
            if (seq1.contains(seq2[i])) {
                answer.append("1 ");
                continue;
            }

            answer.append("0 ");
        }

        System.out.println(answer);
    }

}
