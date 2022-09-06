package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Problem_Tree1_12 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Map<Integer, String> strToStr = new TreeMap<>();
        int n = Integer.parseInt(input.readLine());
        while (n-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            String operator = tokenizer.nextToken();

            if (operator.equals("add")) {
                int key = Integer.parseInt(tokenizer.nextToken());
                String value = tokenizer.nextToken();
                strToStr.put(key, value);
                continue;
            }

            if (operator.equals("find")) {
                int key = Integer.parseInt(tokenizer.nextToken());
                answer.append(strToStr.getOrDefault(key, "None")).append('\n');
                continue;
            }

            if (operator.equals("remove")) {
                int key = Integer.parseInt(tokenizer.nextToken());
                strToStr.remove(key);
                continue;
            }

            if (strToStr.isEmpty()) {
                answer.append("None\n");
                continue;
            }

            Iterator<Integer> keyIterator = strToStr.keySet().iterator();
            while (keyIterator.hasNext()) {
                int key = keyIterator.next();
                answer.append(strToStr.get(key)).append(' ');
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

}
