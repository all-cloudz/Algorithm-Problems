package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Problem_Tree1_13 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Map<String, Integer> cntOfString = new TreeMap<>();
        int n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            String key = input.readLine();
            cntOfString.put(key, cntOfString.getOrDefault(key, 0) + 1);
        }

        for (String key : cntOfString.keySet()) {
            answer.append(String.format("%s %.4f%n", key, 100.0 * cntOfString.get(key) / n));
        }

        System.out.println(answer);
    }

}
