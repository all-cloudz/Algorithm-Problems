package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Problem_Tree1_26 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer= new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        tokenizer.nextToken();
        int M = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Integer> treeSet = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toCollection(TreeSet::new));
        while (M-- > 0) {
            Integer num = treeSet.ceiling(Integer.parseInt(input.readLine()));
            answer.append((num == null) ? -1 : num).append("\n");
        }

        System.out.println(answer);
    }

}
