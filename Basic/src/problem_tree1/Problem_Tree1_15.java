package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Problem_Tree1_15 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Map<Integer, Integer> idxOfInt = new TreeMap<>();
        int n = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            int key = Integer.parseInt(tokenizer.nextToken());
            idxOfInt.putIfAbsent(key, i);
        }

        for (int key : idxOfInt.keySet()) {
//            answer.append(String.format("%d %d%n", key, idxOfInt.get(key)));
            answer.append(key).append(' ').append(idxOfInt.get(key));
        }

        System.out.println(answer);
    }

}
