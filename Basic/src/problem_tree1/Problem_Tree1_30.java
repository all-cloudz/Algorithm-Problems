package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Problem_Tree1_30 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        List<Integer> numList = new LinkedList<>();
        tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            numList.add(Integer.parseInt(tokenizer.nextToken()));
        }

        numList = numList.stream()
                .distinct()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList());

        while (k-- > 0) {
            answer.append(numList.remove(0)).append(" ");
        }

        System.out.println(answer);
    }

}
